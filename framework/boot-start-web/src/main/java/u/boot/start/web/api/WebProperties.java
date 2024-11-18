package u.boot.start.web.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@ConfigurationProperties(prefix = "web")
@Data
public class WebProperties {
    private Api appApi = new Api("/app", "**.controller.app.**");

    private Api adminApi = new Api("/admin", "**.controller.admin.**");

}
