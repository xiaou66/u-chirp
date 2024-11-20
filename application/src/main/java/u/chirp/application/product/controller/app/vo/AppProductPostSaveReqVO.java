package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
public class AppProductPostSaveReqVO {
    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 产品 id
     */
    @NotNull(message = "产品 id 不能为空")
    private Long productId;

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
    private Integer postType;
}
