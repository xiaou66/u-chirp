package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.product.dal.dataobject.ChirpProductPostCommentDO;
import u.chirp.application.product.dal.mysql.ChirpProductPostCommentMapper;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Service
public class ChirpProductPostCommentServiceImpl extends ServiceImpl<ChirpProductPostCommentMapper, ChirpProductPostCommentDO>
        implements IChirpProductPostCommentService {
}
