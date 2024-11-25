package u.chirp.application.core.filecenter.service.bo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/25
 */
@Data
public class FileUrlBO {
    private Long fileId;
    /**
     * 缩略图
     */
    private String thumbnailUrl;

    /**
     * 预览图
     */
    private String previewUrl;

    /**
     * 文件类型
     */
    private String fileType;

}
