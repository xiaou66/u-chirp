package u.chirp.application.mumber.controller.app.vo;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/23
 */
@Data
public class MemberInfoGetRespVO {

    /**
     * 会员 id
     */
    private Long memberId;

    /**
     * 会员名称
     */
    private String memberNickname;

    /**
     * 用户头像
     */
    private String memberAvatar;
}
