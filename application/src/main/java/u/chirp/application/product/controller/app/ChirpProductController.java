package u.chirp.application.product.controller.app;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.exception.util.ServiceExceptionUtil;
import u.boot.start.common.pojo.R;
import u.chirp.application.product.ProductErrorCodeConstants;
import u.chirp.application.product.controller.app.vo.ChirpProductInfoGetRespVO;
import u.chirp.application.product.convert.ChirpProductConvert;
import u.chirp.application.product.dal.dataobject.ChirpProductDO;
import u.chirp.application.product.service.IChirpProductService;

import java.util.Optional;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@RequestMapping("product")
@RestController
public class ChirpProductController {

    @Resource
    private IChirpProductService chirpProductService;

    /**
     * 获取产品信息
     * @tags v1.0.0
     * @param productCode
     * @return
     */
    @GetMapping("info")
    @SaIgnore
    public R<ChirpProductInfoGetRespVO> info(String productCode) {
        ChirpProductDO product = Optional.ofNullable(chirpProductService.getOne(Wrappers.lambdaQuery(ChirpProductDO.class)
                        .eq(ChirpProductDO::getProductCode, productCode)
                        .last("limit 1")))
                .orElseThrow(() -> ServiceExceptionUtil.exception(ProductErrorCodeConstants.PRODUCT_EXISTENCE));
        return R.success(ChirpProductConvert.INSTANCE.toProductInfoGetRespVO(product));
    }
}
