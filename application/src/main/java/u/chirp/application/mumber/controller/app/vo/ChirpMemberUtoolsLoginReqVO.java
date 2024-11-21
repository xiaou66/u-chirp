package u.chirp.application.mumber.controller.app.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class ChirpMemberUtoolsLoginReqVO {
    /**
     * 登录客户端
     */
    @NotBlank(message = "客户端 ID 不能为空")
    private String clientId;

    /**
     * 用户 token
     */
    @NotBlank(message = "用户 token 不能为空")
    private String accessToken;

    /**
     * 签名
     */
    @NotBlank(message = "sign 不能为空")
    private String sign;
}
