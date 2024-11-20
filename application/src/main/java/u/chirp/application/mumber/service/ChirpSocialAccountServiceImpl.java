package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.mumber.dal.dataobject.ChirpSocialAccountDO;
import u.chirp.application.mumber.dal.mysql.ChirpSocialAccountMapper;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Service
public class ChirpSocialAccountServiceImpl extends ServiceImpl<ChirpSocialAccountMapper, ChirpSocialAccountDO>
        implements IChirpSocialAccountService{
}
