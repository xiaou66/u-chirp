package u.chirp.application.core.filecenter.dal.dataobejct;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
@TableName("chirp_file_library")
public class ChirpFileLibraryDO extends BaseDO<ChirpFileLibraryDO> {
    /**
     * 文件ID
     */
    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;

    /**
     * 存储桶
     */
    @TableField("bucket")
    private String bucket;

    /**
     * 端点
     */
    @TableField("endpoint")
    private String endpoint;

    /**
     * 资源 URI
     */
    @TableField("uri")
    private String uri;

    /**
     * 上传源
     */
    @TableField("upload_source")
    private String uploadSource;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 文件大小, 单位 B
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * MIME 类型
     */
    @TableField("mime_type")
    private String mimeType;

    /**
     * 上传文件名称
     */
    @TableField("upload_file_name")
    private String uploadFileName;

    /**
     * 原始文件名称
     */
    @TableField("origin_file_name")
    private String originFileName;
}
