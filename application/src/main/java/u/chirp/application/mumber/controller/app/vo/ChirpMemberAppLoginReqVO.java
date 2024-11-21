package u.chirp.application.mumber.controller.app.vo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class ChirpMemberAppLoginReqVO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 第三方 id
     */
    private String openId;

    /**
     * 签名
     */
    private String sign;
}
