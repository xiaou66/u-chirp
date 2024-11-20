package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberAccountDO;
import u.chirp.application.mumber.dal.mysql.ChirpMemberAccountMapper;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Service
public class ChirpMemberAccountServiceImpl extends ServiceImpl<ChirpMemberAccountMapper, ChirpMemberAccountDO>
        implements IChirpMemberAccountService{
}
