package u.chirp.application.product.controller.app.vo;

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
    private boolean thumbsUp;
}
