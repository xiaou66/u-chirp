package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.product.dal.dataobject.ChirpProductDO;
import u.chirp.application.product.dal.mysql.ChirpProductMapper;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@Service
public class ChirpProductServiceImpl extends ServiceImpl<ChirpProductMapper, ChirpProductDO>
        implements IChirpProductService{
}
