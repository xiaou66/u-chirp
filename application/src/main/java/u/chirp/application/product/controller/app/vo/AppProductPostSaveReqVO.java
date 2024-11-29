package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
public class AppProductPostSaveReqVO extends AppProductPostBaseReqVO {
    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 帖子标题
     */
    private String postTitle;

    /**
     * 原始内容 Delta 格式
     */
    private String postRawContent;

    /**
     * 原始 html 内容
     */
    private String postRawHtml;

    /**
     * 帖子类型
     */
    private String postType;

    /**
     * 文件上传后拿到的文件ids
     */
    private List<Long> fileIds;
}
