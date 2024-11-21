package u.chirp.application.core.filecenter.dal.dataobejct;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class ChirpFileManagerDO extends BaseDO<ChirpFileManagerDO> {
    /**
     * 文件管理 id
     */
    @TableId(value = "file_manager_id", type = IdType.AUTO)
    private Long fileManagerId;

    /**
     * 文件 code
     */
    @TableField("func_code")
    private String funcCode;

    /**
     * 关联 id
     */
    @TableField("ref_id")
    private Long refId;

    /**
     * 文件 id
     */
    @TableField("file_id")
    private Long fileId;
}
