package u.chirp.application.mumber.controller.admin;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class SocialAccountDeleteReqVO {
    @NotNull(message = "socialId 不能为空")
    private Integer socialId;
}
