package u.chirp.application.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        if (Objects.equals(loginId, AuthConstant.ADMIN_USER_ID)) {
            return List.of("admin");
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        if (Objects.equals(loginId, AuthConstant.ADMIN_USER_ID)) {
            return List.of("admin");
        }

        return Collections.emptyList();
    }
}
