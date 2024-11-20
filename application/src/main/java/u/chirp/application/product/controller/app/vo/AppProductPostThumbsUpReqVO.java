package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
public class AppProductPostThumbsUpReqVO extends AppProductPostActionReqVO {
    /**
     * 是否点赞
     */
    @NotNull(message = "是否点赞 不能为 null")
    private Boolean thumbsUp;
}
