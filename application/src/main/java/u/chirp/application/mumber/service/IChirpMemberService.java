package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberLoginReqVO;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;
import u.chirp.application.mumber.service.bo.GenerateMemberBO;
import u.chirp.application.mumber.service.bo.MemberLoginSuccessInfoBO;

/**
 * @author xiaou
 * @date 2024/11/17
 */
public interface IChirpMemberService extends IService<ChirpMemberDO> {
    /**
     * 登录
     *
     * @param reqVo
     * @return
     */
    MemberLoginSuccessInfoBO login(ChirpMemberLoginReqVO reqVo);

    Long generateUser(GenerateMemberBO generateMemberBO);

    void loginAfter(Long memberId);
}
