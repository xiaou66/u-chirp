package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.mumber.dal.dataobject.ChirpSocialAccountDO;

/**
 * @author xiaou
 * @date 2024/11/20
 */
public interface IChirpSocialAccountService extends IService<ChirpSocialAccountDO> {


    ChirpSocialAccountDO getByClientId(String clientId);
}
