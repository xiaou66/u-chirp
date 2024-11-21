package u.chirp.application.product.controller.app.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class AppProductPostListGetReqVO {
    /**
     * 产品 code
     */
    private String productCode;
}
