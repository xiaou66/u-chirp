package u.chirp.application.product.controller.app.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
public class AppProductPostBaseReqVO {
    /**
     * 产品 code
     */
    @NotNull(message = "产品 code 不能为空")
    private String productCode;
}
