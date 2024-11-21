package u.chirp.application.mumber.service.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class GenerateMemberBO {
    /**
     * 会员名称
     */
    private String memberNickname;

    /**
     * 用户头像
     */
    private String memberAvatar;
}
