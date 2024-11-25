package u.chirp.application.core.sys.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Data
@TableName("system_i18_config")
public class SystemI18ConfigDO extends BaseDO<SystemI18ConfigDO> {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 语言
     */
    private String language;

    /**
     * 节点类型1-普通节点2-父节点
     */
    private Integer nodeType;

    /**
     * 父 id
     */
    private Long parentId;

    /**
     * name
     */
    private String name;

    /**
     * value
     */
    private String value;
}
