package u.chirp.application.product.controller.app.vo;

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
public class AppProductPostListGetReqVO extends RollParam {
    /**
     * 产品 code
     */
    @NotBlank(message = "产品 code 不能为空")
    private String productCode;

    /**
     * 帖子名称
     */
    private String postTitle;

    /**
     * tab
     */
    private AppProductPostListTab tab;

}
