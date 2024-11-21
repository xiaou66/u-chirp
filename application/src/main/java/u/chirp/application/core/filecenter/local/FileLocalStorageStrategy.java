package u.chirp.application.core.filecenter.local;

import cn.hutool.core.util.IdUtil;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import jakarta.annotation.Resource;
import org.apache.tika.Tika;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import u.boot.start.common.exception.util.ServiceExceptionUtil;
import u.boot.start.common.utils.FilePathUtils;
import u.chirp.application.core.filecenter.IFileStorageStrategy;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileLibraryDO;
import u.chirp.application.core.filecenter.local.vo.FilePreviewReqVO;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;
import u.chirp.application.core.filecenter.service.IChirpFileLibraryService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Component
@Validated
public class FileLocalStorageStrategy implements IFileStorageStrategy {

    private final static File localDir = new File(FilePathUtils.getData0(), "upload");

    @Resource
    private IChirpFileLibraryService chirpFileLibraryService;


    @Override
    public void uploadFile(MultipartFile file) {
        if (!localDir.exists()) {
            localDir.mkdirs();
        }

        ChirpFileLibraryDO chirpFileLibrary = new ChirpFileLibraryDO();

        chirpFileLibrary.setUploadSource(getUpdateSource());
        chirpFileLibrary.setUri("");
        chirpFileLibrary.setFileSize(file.getSize());
        chirpFileLibrary.setOriginFileName(file.getName());
        chirpFileLibrary.setFileType(Files.getFileExtension(file.getName()));
        chirpFileLibrary.setMimeType(file.getContentType());
        String newFileName = IdUtil.getSnowflakeNextId() + "-" + LocalDateTime.now() + '-' + file.getName();
        chirpFileLibrary.setUploadFileName(newFileName);
        File destinationFile = new File(localDir, newFileName);
        try {
            // 将上传的文件复制到指定的 localFile 目录
            Files.copy(file.getResource().getFile(), destinationFile);
        } catch (IOException e) {
            // 处理文件复制异常
            throw ServiceExceptionUtil.exception(FileCenterErrorCodeConstants.FILE_UPLOAD_ERROR);
        }
        chirpFileLibraryService.save(chirpFileLibrary);
    }

    @Override
    public FileUrlVO generateUrl(String fileId) {
        ChirpFileLibraryDO fileLibrary = chirpFileLibraryService.getById(fileId);
        FileUrlVO fileUrlVO = new FileUrlVO();
        fileUrlVO.setPreviewUrl(fileLibrary.getOriginFileName());
        fileUrlVO.setThumbnailUrl(fileLibrary.getUploadFileName());
        return fileUrlVO;
    }


    @GetMapping("/preview")
    public ResponseEntity<?> previewFile(@Validated FilePreviewReqVO reqVO) {
        File file = new File(localDir, reqVO.getKey());
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("文件未找到");
        }

        try {
            // 获取文件的 MIME 类型
            Tika tika = new Tika();
            String mimeType = tika.detect(file);
            if (mimeType == null) {
                mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE; // 默认 MIME 类型
            }

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, mimeType);
            headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

            // 返回文件内容
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(Files.newReader(file, Charsets.UTF_8)); // 读取文件内容并返回
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
