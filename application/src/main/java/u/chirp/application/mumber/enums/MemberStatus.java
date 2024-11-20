package u.chirp.application.mumber.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@AllArgsConstructor
@Getter
public enum MemberStatus {
    /**
     * 封禁
     */
    BAN(0),
    /**
     * 正常
     */
    NORMAL(1),
    ;
    @EnumValue
    private final Integer value;
}
