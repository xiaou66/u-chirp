package u.chirp.application.core.filecenter;

import org.springframework.web.multipart.MultipartFile;
import u.chirp.application.core.filecenter.service.bo.FileUrlBO;

/**
 * @author xiaou
 * @date 2024/11/21
 */
public interface IFileStorageStrategy {


    /**
     * 上传文件
     * @param file 文件
     */
    default void uploadFile(MultipartFile file) {}

    /**
     * 生成文件链接
     *
     * @param fileId 文件 id
     * @return
     */
    FileUrlBO generateUrl(Long fileId);


    /**
     * 上传源
     * @return
     */
    String getUpdateSource();
}
