package u.chirp.application.core.filecenter.local.vo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class FileUrlVO {
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
