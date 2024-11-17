package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;
import u.chirp.application.mumber.dal.mysql.ChirpMemberMapper;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@Service
public class ChirpMemberServiceImpl extends ServiceImpl<ChirpMemberMapper, ChirpMemberDO>
        implements IChirpMemberService{
}
