package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
public class AppProductPostActionReqVO {
    /**
     * 产品 id
     */
    @NotNull(message = "产品 id 不能为空")
    private Long productId;

    /**
     * 帖子 id
     */
    @NotNull(message = "帖子 id 不能为空")
    private Long postId;
}
