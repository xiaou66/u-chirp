package u.chirp.application.mumber.controller.admin.vo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class SocialAccountListGetRespVO {
    /**
     * 社交账号ID
     */
    private Long socialId;

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
     * 代理编号
     */
    private String socialAgentId;

    /**
     *  1-正常, 0-停用
     */
    private Byte socialStatus;
}
