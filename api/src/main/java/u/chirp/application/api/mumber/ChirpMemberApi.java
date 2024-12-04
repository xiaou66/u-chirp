package u.chirp.application.api.mumber;

import u.chirp.application.api.mumber.dto.MemberAddReqDTO;

/**
 * 会员
 * @author xiaou
 * @date 2024/12/4
 */
public interface ChirpMemberApi {
    /**
     * 添加会员
     * @param dto
     */
    void addMember(MemberAddReqDTO dto);
}
