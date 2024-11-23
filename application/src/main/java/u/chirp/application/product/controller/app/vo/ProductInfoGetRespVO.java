package u.chirp.application.product.controller.app.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/23
 */
@Data
public class ProductInfoGetRespVO {
    /**
     * 产品 logo
     */
    private String productLogo;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品 code
     */
    private String productCode;

    /**
     * 产品状态
     */
    private Integer productStatus;
}
