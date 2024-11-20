package u.chirp.application.product.controller.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
public class ChirpProductItemRespVO {
    /**
     * 产品主键 id
     */
    @TableId(value = "product_id", type = IdType.ASSIGN_ID)
    private Long productId;

    /**
     * 产品 logo
     */
    @TableField("product_logo")
    private String productLogo;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 产品 code
     */
    @TableField("product_code")
    private String productCode;

    /**
     * 产品状态
     */
    @TableField("product_status")
    private Integer productStatus;
}
