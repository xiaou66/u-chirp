package u.chirp.application.mumber.service.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 会员基本信息用于公共的信息显示
 * @author xiaou
 * @date 2024/11/25
 */
@Data
public class ChirpMemberBaseInfoBO implements Serializable {
    @Serial
    private static final long serialVersionUID = 17325463294771231L;

    /**
     * 会员 id
     */
    private Long memberId;

    /**
     * 会员名称
     */
    private String memberNickname;

    /**
     * 用户头像
     */
    private String memberAvatar;
}
