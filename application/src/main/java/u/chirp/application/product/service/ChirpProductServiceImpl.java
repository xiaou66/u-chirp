package u.chirp.application.product.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.product.controller.admin.vo.ChirpProductItemRespVO;
import u.chirp.application.product.controller.app.vo.ChirpProductCreateReqVO;
import u.chirp.application.product.convert.ChirpProductConvert;
import u.chirp.application.product.dal.dataobject.ChirpProductDO;
import u.chirp.application.product.dal.mysql.ChirpProductMapper;

import java.util.List;

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

    @Override
    public List<ChirpProductItemRespVO> productList() {
        List<ChirpProductDO> list = list(Wrappers.lambdaQuery(ChirpProductDO.class)
                .eq(ChirpProductDO::getCreator, StpUtil.getLoginIdAsLong()));
        // TODO: 这里有文件链接需要处理
        return ChirpProductConvert.INSTANCE.convert(list);
    }

    private ChirpProductDO initProduct() {
        ChirpProductDO chirpProductDO = new ChirpProductDO();
        chirpProductDO.setProductCode(IdUtil.nanoId(12));
        chirpProductDO.setProductStatus(1);
        return chirpProductDO;
    }
}
