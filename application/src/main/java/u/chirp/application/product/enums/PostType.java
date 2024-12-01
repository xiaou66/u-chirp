package u.chirp.application.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaou
 * @date 2024/11/30
 */
@AllArgsConstructor
@Getter
public enum PostType {
    /**
     * bug
     */
    BUG("bug"),
    /**
     * 想法
     */
    IDEA("idea"),
    /**
     * 投票
     */
    ROAST("roast"),
    ;
    private final String value;
}
