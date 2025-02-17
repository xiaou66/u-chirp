package u.chirp.application.product.service.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import u.boot.start.common.pojo.PageParam;
import u.boot.start.common.pojo.RollParam;
import u.chirp.application.product.enums.AppProductPostListTab;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class AppProductPostListBO extends PageParam {

    /**
     * 产品 id
     */
    private Long productId;

    /**
     * 帖子名称
     */
    private String postTitle;

    /**
     * tab
     */
    private AppProductPostListTab tab;

    /**
     * 会员 id
     */
    private Long memberId;

    private Boolean joinCollectTable = false;
}
