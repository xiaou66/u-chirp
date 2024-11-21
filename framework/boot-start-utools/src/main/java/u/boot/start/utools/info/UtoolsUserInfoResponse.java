package u.boot.start.utools.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.xiaou66.sdk.BaseResponse;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/2/22
 */

@Data
public class UtoolsUserInfoResponse implements BaseResponse {
    /**
     * 头像
     */
    private String avatar;

    /**
     * 是否 uTools 会员（0: 否，1: 是）
     */
    private Long member;

    /**
     * 昵称
     */
    private String nickname;

    /**
     *  uTools 用户 ID, 对于此插件应用不变且唯一
     */
    @JsonProperty("open_id")
    private String openId;

}
