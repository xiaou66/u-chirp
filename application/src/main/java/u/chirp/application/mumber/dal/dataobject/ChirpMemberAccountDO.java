package u.chirp.application.mumber.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
@TableName("chirp_member_account")
public class ChirpMemberAccountDO extends BaseDO<ChirpMemberAccountDO> {
    /**
     * 会员账号 ID
     */
    @TableId(value = "account_id", type = IdType.ASSIGN_ID)
    private Long accountId;

    /**
     * 社交客户端 ID
     */
    @TableField("social_id")
    private Long socialId;

    /**
     * 第三方用户 ID
     */
    @TableField("open_id")
    private String openId;

    /**
     * 绑定 id
     */
    @TableField("bind_member_id")
    private Long bindMemberId;

    /**
     * 账号名称
     */
    @TableField("account_name")
    private String accountName;

    /**
     * 账号头像
     */
    @TableField("account_avatar")
    private String accountAvatar;

    /**
     * 账号状态, 1-正常, 0-停用
     */
    @TableField("account_status")
    private boolean accountStatus;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 账号原始数据
     */
    @TableField(value = "account_raw_data", select = false)
    private String accountRawData;
}
