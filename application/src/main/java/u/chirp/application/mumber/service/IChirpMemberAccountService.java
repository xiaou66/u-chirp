package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberAppLoginReqVO;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberAccountDO;
import u.chirp.application.mumber.dal.dataobject.ChirpSocialAccountDO;
import u.chirp.application.mumber.service.bo.MemberLoginSuccessInfoBO;

/**
 * @author xiaou
 * @date 2024/11/20
 */
public interface IChirpMemberAccountService extends IService<ChirpMemberAccountDO> {
    MemberLoginSuccessInfoBO thirdPartyLogin(ChirpMemberAppLoginReqVO reqVo, ChirpSocialAccountDO chirpSocialAccount);

}
