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
    ;

    @EnumValue
    private final int value;
}
