package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/12/3
 */
@Data
public class AppProductPostPostTypeModifyReqVO extends AppProductPostActionReqVO {
    /**
     * 帖子类型
     */
    @NotBlank(message = "postType 不能为空")
    private String postType;
}
