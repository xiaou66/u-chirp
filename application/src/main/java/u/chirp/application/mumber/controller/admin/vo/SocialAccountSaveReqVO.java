package u.chirp.application.mumber.controller.admin.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class SocialAccountSaveReqVO {
    /**
     * 更新使用
     */
    private Integer socialId;

    /**
     * 关联产品
     */
    private Long socialProductId;

    /**
     * 社交平台的类型
     */
    private String socialType;

    /**
     * 客户端 clientId
     */
    private String socialClientId;

    /**
     * 客户端密钥
     */
    private String socialClientSecret;

    /**
     * 代理编号
     */
    private String socialAgentId;

    /**
     *  1-正常, 0-停用
     */
    private Byte socialStatus;

}
