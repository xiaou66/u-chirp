package u.chirp.application.auth;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 路由拦截器
 * @author xiaou
 * @date 2024/11/20
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> {
                    StpUtil.checkLogin();
                    StpUtil.checkPermission("admin");
                })).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/system/login", "/admin/system/init");
    }
}
