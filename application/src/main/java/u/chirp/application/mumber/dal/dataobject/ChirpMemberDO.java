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
    @TableField("number_nickname")
    private String numberNickname;

    /**
     * 用户头像
     */
    @TableField("number_avatar")
    private String numberAvatar;

    /**
     * 会员用户名
     */
    @TableField("number_username")
    private String numberUsername;

    /**
     * 会员密码
     */
    @TableField("number_password")
    private String numberPassword;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 会员状态
     */
    @TableField("number_status")
    private Byte numberStatus;
}
