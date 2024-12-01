package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/30
 */
@Data
public class ChirpProductPostGetReqVO extends AppProductPostBaseReqVO {
    /**
     * 帖子 id
     */
    @NotNull(message = "帖子 id 不能为空")
    private Long postId;
}
