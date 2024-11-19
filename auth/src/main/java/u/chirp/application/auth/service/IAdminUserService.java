package u.chirp.application.auth.service;

import jakarta.validation.constraints.NotBlank;
import u.chirp.application.auth.controller.admin.vo.SystemAdminLoginReqVO;
import u.chirp.application.auth.controller.admin.vo.SystemInitReqVo;

/**
 * @author xiaou
 * @date 2024/11/19
 */
public interface IAdminUserService {
    /**
     * 超级管理员账号初始化
     * @param reqVo
     */
    void adminAccountInit(SystemInitReqVo reqVo);


    /**
     * 超级管理员登录
     * @param reqVo
     * @param identity
     * @return
     */
    String login(SystemAdminLoginReqVO reqVo, String identity);
}
