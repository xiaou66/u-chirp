package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/27
 */
@Data
public class AppProductFollowRecordGetReqVO {
    /**
     * 帖子id
     */
    @NotNull(message = "postIds 不能为空")
    private List<Long> postIds;
}
