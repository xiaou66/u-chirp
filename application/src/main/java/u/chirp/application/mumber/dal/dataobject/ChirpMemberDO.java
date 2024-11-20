package u.chirp.application.mumber.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@TableName("chirp_member")
@Data
public class ChirpMemberDO extends BaseDO<ChirpMemberDO> {
    /**
     * 会员 id
     */
    @TableId(value = "member_id", type = IdType.ASSIGN_ID)
    private Long memberId;

    /**
     * 会员名称
     */
    @TableField("member_nickname")
    private String memberNickname;

    /**
     * 用户头像
     */
    @TableField("member_avatar")
    private String memberAvatar;

    /**
     * 会员密码
     */
    @TableField("member_password")
    private String memberPassword;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    @TableField("email")
    private String email;

    /**
     * 会员状态
     */
    @TableField("member_status")
    private Integer memberStatus;
}
