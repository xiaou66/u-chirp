package u.chirp.application.mumber.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;
import u.chirp.application.mumber.enums.CollectType;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
@TableName("chirp_member_collect")
public class ChirpMemberCollectDO extends BaseDO<ChirpMemberCollectDO> {
    /**
     * 收集 id
     */
    @TableId(value = "collect_id")
    private Long collectId;

    /**
     * 收藏会员 id
     */
    @TableField("collect_member_id")
    private Long collectMemberId;

    /**
     * 收集类型
     * @see CollectType
     */
    @TableField("collect_type")
    private Integer collectType;

    /**
     * 收集 id
     */
    @TableField("collect_ref_id")
    private Long collectRefId;
}
