package u.chirp.application.core.sys.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Data
@TableName("system_dict_type")
public class SystemDictTypeDO extends BaseDO<SystemDictTypeDO> {
    /**
     * 字典主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 字典名称
     * 不能为空，默认值为 ''
     */
    private String dictName;

    /**
     * 字典类型
     * 不能为空，默认值为 ''
     */
    private String sourceType;

    /**
     * 状态
     * 0 表示正常，1 表示停用，默认值为 0
     */
    private Boolean dictStatus;

    /**
     * 备注
     * 可为空
     */
    private String dictRemark;

    /**
     * 是否删除
     */
    @TableLogic
    private Boolean delete;

}
