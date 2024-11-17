package u.chirp.application.product.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@Data
@TableName("chirp_product")
public class ChirpProductDO extends BaseDO<ChirpProductDO> {
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
    private Byte productStatus;

    /**
     * 产品 appKey
     */
    @TableField("product_app_key")
    private String productAppKey;

    /**
     * 产品 secretKey
     */
    @TableField("product_secret_key")
    private String productSecretKey;
}
