package u.chirp.application.mumber.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@AllArgsConstructor
@Getter
public enum SocialType {
    /**
     * 自建
     */
    SELF_BUILD("selfBuild"),
    /**
     * utools
     */
    UTOOLS("utools"),
    ;
    private final String value;
}
