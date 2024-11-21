package u.chirp.application.mumber.controller.app.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class ChirpMemberThirdPartyLoginReqVO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 登录客户端
     */
    @NotBlank(message = "客户端 ID 不能为空")
    private String clientId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 第三方 id
     */
    @NotBlank(message = "第三方 id 不能为空")
    private String openId;

    /**
     * 签名
     */
    @NotBlank(message = "签名 不能为空")
    private String sign;

}
