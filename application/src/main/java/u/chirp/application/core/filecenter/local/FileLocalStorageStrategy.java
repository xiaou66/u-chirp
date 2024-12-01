package u.chirp.application.core.filecenter.local;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.IdUtil;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import u.boot.start.common.exception.enums.GlobalErrorCodeConstants;
import u.boot.start.common.exception.util.ServiceExceptionUtil;
import u.boot.start.common.exception.util.json.JsonUtils;
import u.boot.start.common.pojo.R;
import u.boot.start.common.utils.FilePathUtils;
import u.chirp.application.core.filecenter.IFileStorageStrategy;
import u.chirp.application.core.filecenter.convert.FileLibraryConvert;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileLibraryDO;
import u.chirp.application.core.filecenter.local.bo.LocalFilePreviewParams;
import u.chirp.application.core.filecenter.local.vo.FilePreviewReqVO;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;
import u.chirp.application.core.filecenter.service.IChirpFileLibraryService;
import u.chirp.application.core.filecenter.service.bo.FileUrlBO;
import u.chirp.application.core.sys.config.EncryptProperties;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @folder app/文件管理/本地生成
 * @author xiaou
 * @date 2024/11/21
 */
@Slf4j
@Validated
@Controller
@RequestMapping("app/")
public class FileLocalStorageStrategy implements IFileStorageStrategy {

    private final static File localDir = new File(FilePathUtils.getData0(), "upload");

    @Resource
    private IChirpFileLibraryService chirpFileLibraryService;

    @Resource
    private EncryptProperties encryptProperties;


    /**
     * 上传文档文件
     * @param file
     * @return
     */
    @RequestMapping("/local/uploadFile")
    @ResponseBody
    public R<FileUrlVO> localUploadFile(MultipartFile file) {
        FileUrlBO fileUrlBO = uploadFile(file);
        return R.success(FileLibraryConvert.INSTANCE.toFileUrlVO(fileUrlBO));
    }

    @Override
    public FileUrlBO uploadFile(MultipartFile file) {
        if (!localDir.exists()) {
            localDir.mkdirs();
        }

        ChirpFileLibraryDO chirpFileLibrary = new ChirpFileLibraryDO();

        chirpFileLibrary.setUploadSource(getUpdateSource());
        chirpFileLibrary.setUri("");
        chirpFileLibrary.setFileSize(file.getSize());
        chirpFileLibrary.setOriginFileName(file.getOriginalFilename());
        chirpFileLibrary.setFileType(Files.getFileExtension(file.getOriginalFilename()));
        chirpFileLibrary.setMimeType(file.getContentType());
        String newFileName = IdUtil.getSnowflakeNextId() + "-" + System.currentTimeMillis() + '-' + file.getOriginalFilename();
        chirpFileLibrary.setUploadFileName(newFileName);
        File destinationFile = new File(localDir, newFileName);
        try {
            // 将上传的文件复制到指定的 localFile 目录
            file.transferTo(destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
            // 处理文件复制异常
            throw ServiceExceptionUtil.exception(FileCenterErrorCodeConstants.FILE_UPLOAD_ERROR);
        }
        chirpFileLibraryService.save(chirpFileLibrary);
        return generateUrl(chirpFileLibrary.getFileId());
    }

    @Override
    public FileUrlBO generateUrl(Long fileId) {
        ChirpFileLibraryDO fileLibrary = chirpFileLibraryService.getById(fileId);
        LocalFilePreviewParams previewUrlParams = new LocalFilePreviewParams();
        previewUrlParams.setFileId(fileId);
        FileUrlBO fileUrlBo = new FileUrlBO();
        fileUrlBo.setFileId(fileId);
        String secKey = URLEncoder.encode(SaSecureUtil.aesEncrypt(encryptProperties.getAseKey(), JsonUtils.toJsonString(previewUrlParams)), Charsets.UTF_8);
        fileUrlBo.setPreviewUrl("/app/local/preview?key=" + secKey);
        fileUrlBo.setThumbnailUrl(fileLibrary.getUploadFileName());
        return fileUrlBo;
    }



    @GetMapping("/local/preview")
    @SaIgnore
    public ResponseEntity<?> previewFile(@Validated FilePreviewReqVO reqVO) {
        LocalFilePreviewParams localFilePreviewParams = null;
        try {
            String decKey = URLDecoder.decode(SaSecureUtil.aesDecrypt(encryptProperties.getAseKey(), reqVO.getKey()), Charsets.UTF_8);
            localFilePreviewParams = JsonUtils.parseObject(decKey, LocalFilePreviewParams.class);
        } catch (Exception e) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }

        if (Objects.isNull(localFilePreviewParams) || Objects.isNull(localFilePreviewParams.getFileId())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }

        ChirpFileLibraryDO fileLibrary = chirpFileLibraryService.getById(localFilePreviewParams.getFileId());
        File file = new File(localDir, fileLibrary.getUploadFileName());
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("文件未找到");
        }

        try {
            // 获取文件的 MIME 类型
            Tika tika = new Tika();
            String mimeType = tika.detect(file);
            if (mimeType == null) {
                // 默认 MIME 类型
                mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, mimeType);
            headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
            String encodedFileName = URLEncoder.encode(fileLibrary.getOriginFileName(), StandardCharsets.UTF_8);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");

            FileSystemResource resource = new FileSystemResource(file);
            // 返回文件内容
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件预览失败");
        }
    }



    /**
     * 上传源
     *
     * @return
     */
    @Override
    public String getUpdateSource() {
        return "local";
    }
}
