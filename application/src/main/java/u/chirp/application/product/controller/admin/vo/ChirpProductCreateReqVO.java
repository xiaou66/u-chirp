package u.chirp.application.product.controller.admin.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/19
 */
@Data
public class ChirpProductCreateReqVO {
    /**
     * 产品 logo
     */
    private String productLogo;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空")
    private String productName;
}
