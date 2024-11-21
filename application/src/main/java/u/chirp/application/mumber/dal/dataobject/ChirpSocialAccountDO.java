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
@TableName("chirp_social_account")
public class ChirpSocialAccountDO extends BaseDO<ChirpSocialAccountDO> {
    /**
     * 社交账号ID
     */
    @TableId(value = "social_id", type = IdType.ASSIGN_ID) //
    private Long socialId;

    @TableField("social_product_id")
    private Long socialProductId;

    /**
     * 社交平台的类型
     */
    @TableField("social_type")
    private String socialType;

    /**
     * 客户端 clientId
     */
    @TableField("social_client_id")
    private String socialClientId;

    /**
     * 客户端密钥
     */
    @TableField("social_client_secret")
    private String socialClientSecret;

    /**
     * 代理编号
     */
    @TableField("social_agent_id")
    private String socialAgentId;

    /**
     *  1-正常, 0-停用
     */
    @TableField("social_status")
    private Byte socialStatus;
}
