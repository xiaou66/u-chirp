package u.chirp.application.product.service.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import u.chirp.application.product.enums.AppProductPostListTab;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class AppProductPostListBO {

    /**
     * 产品 code
     */
    @NotBlank(message = "产品 code 不能为空")
    private String productCode;

    /**
     * 帖子名称
     */
    private String postName;

    /**
     * tab
     */
    private AppProductPostListTab tab;


}
