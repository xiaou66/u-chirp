package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class AppProductPostCollectReqVO extends AppProductPostActionReqVO {
    /**
     * 是否收集
     */
    @NotNull(message = "收集不能为空")
    private Boolean collect;
}
