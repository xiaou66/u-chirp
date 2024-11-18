package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.product.dal.dataobject.ChirpProductMemberDO;
import u.chirp.application.product.dal.mysql.ChirpProductMemberMapper;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Service
public class ChirpProductMemberServiceImpl extends ServiceImpl<ChirpProductMemberMapper, ChirpProductMemberDO>
        implements IChirpProductMemberService {
}
