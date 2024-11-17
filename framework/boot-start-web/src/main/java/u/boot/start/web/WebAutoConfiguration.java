package u.boot.start.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import u.boot.start.web.api.Api;
import u.boot.start.web.api.WebProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@Data
@AutoConfiguration
@EnableConfigurationProperties(WebProperties.class)
public class WebAutoConfiguration implements WebMvcConfigurer {
    /**
     * 应用名
     */
    @Value("${spring.application.name}")
    private String applicationName;


    /**
     * 设置 API 前缀，仅仅匹配 controller 包下的
     *
     * @param configurer 配置
     * @param api        API 配置
     */
    private void configurePathMatch(PathMatchConfigurer configurer, Api api) {
        AntPathMatcher antPathMatcher = new AntPathMatcher(".");
        List<String> controllerList = Arrays.asList(api.getController().split("\\|"));
        configurer.addPathPrefix(api.getPrefix(), (clazz) -> clazz.isAnnotationPresent(RestController.class)
                && controllerList.stream().anyMatch(controller -> antPathMatcher.match(controller, clazz.getPackage().getName()))); // 仅仅匹配 controller 包
    }
}
