package u.chirp.application.system.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.chirp.application.core.dal.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/7
 */
@Data
@TableName("sys_user")
public class SysUserDO extends BaseDO<SysUserDO> {
    /**
     * 用户 id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户账号状态
     */
    private Integer userStatus;
}
