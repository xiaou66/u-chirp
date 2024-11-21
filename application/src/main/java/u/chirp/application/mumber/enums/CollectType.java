package u.chirp.application.mumber.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 收集类型
 * @author xiaou
 * @date 2024/11/20
 */
@AllArgsConstructor
@Getter
public enum CollectType {
    /**
     * 点赞帖子
     */
    THUMBS_UP_POST(1),
    /**
     * 收藏帖子
     */
    COLLECT_POST(2),
    ;

    @EnumValue
    private final Integer value;
}
