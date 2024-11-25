package u.chirp.application.core.sys.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiaou
 * @date 2024/11/25
 */
@Component
@ConfigurationProperties(prefix = "encrypt")
@Data
public class EncryptProperties {
    /**
     * aseKey
     */
    private String aseKey;
}
