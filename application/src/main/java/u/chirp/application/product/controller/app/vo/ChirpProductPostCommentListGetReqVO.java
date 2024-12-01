package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import u.boot.start.common.pojo.RollParam;

/**
 * @author xiaou
 * @date 2024/11/30
 */
@Data
public class ChirpProductPostCommentListGetReqVO extends RollParam {

    /**
     * 产品 code
     */
    @NotNull(message = "产品 code 不能为空")
    private String productCode;


    /**
     * 帖子 id
     */
    @NotNull(message = "帖子 id 不能为空")
    private Long postId;

    /**
     * 搜索
     */
    private String keyword;

    /**
     * 父评论 id
     */
    private String parentId;
}
