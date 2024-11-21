package u.chirp.application.mumber.controller.admin;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class SocialClientSecretGetReqVO {
    @NotNull(message = "socialId 不能为空")
    private Integer socialId;
}
