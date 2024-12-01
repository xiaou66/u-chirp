package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import u.boot.start.common.pojo.RollParam;

/**
 * @author xiaou
 * @date 2024/12/1
 */
@Data
public class ChirpProductPostCommentChildrenListGetReqVO extends RollParam {
    @NotNull(message = "父评论 id")
    private Long commentId;
}
