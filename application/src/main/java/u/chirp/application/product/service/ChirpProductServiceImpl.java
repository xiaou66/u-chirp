package u.chirp.application.product.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.product.controller.app.vo.ChirpProductCreateReqVO;
import u.chirp.application.product.dal.dataobject.ChirpProductDO;
import u.chirp.application.product.dal.mysql.ChirpProductMapper;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@Service
public class ChirpProductServiceImpl extends ServiceImpl<ChirpProductMapper, ChirpProductDO>
        implements IChirpProductService {


    @Override
    public Long create(ChirpProductCreateReqVO reqVo) {
        ChirpProductDO chirpProductDO = initProduct();
        chirpProductDO.setProductName(reqVo.getProductName());
        chirpProductDO.setProductLogo(reqVo.getProductLogo());
        chirpProductDO.setCreator(StpUtil.getLoginIdAsLong());
        save(chirpProductDO);
        return chirpProductDO.getProductId();
    }

    private ChirpProductDO initProduct() {
        ChirpProductDO chirpProductDO = new ChirpProductDO();
        chirpProductDO.setProductCode(IdUtil.nanoId(12));
        chirpProductDO.setProductStatus(1);
        return chirpProductDO;
    }
}
