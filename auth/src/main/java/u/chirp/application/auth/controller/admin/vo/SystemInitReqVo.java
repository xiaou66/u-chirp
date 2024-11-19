package u.chirp.application.auth.controller.admin.vo;

import jakarta.validation.constraints.NotBlank;
import jdk.jfr.DataAmount;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/19
 */
@DataAmount
@Data
public class SystemInitReqVo {
    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称 不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码 不能为空")
    private String password;

    /**
     * 身份 code
     */
    @NotBlank(message = "身份 code 不能为空")
    private String identityCode;
}
