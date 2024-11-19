package u.chirp.application.auth.controller.admin.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/19
 */
@Data
public class SystemAdminLoginReqVO {
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
}
