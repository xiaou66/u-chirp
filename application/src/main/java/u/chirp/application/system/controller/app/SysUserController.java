package u.chirp.application.system.controller.app;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaou
 * @date 2024/11/7
 */
@RestController
@RequestMapping("user")
public class SysUserController {

    /**
     * 用户登录
     * @return
     */
    @PostMapping("/login")
    public String test() {
        return "ok";
    }
}
