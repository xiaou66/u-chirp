package u.chirp.application.product.controller.app;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.product.controller.app.vo.ChirpProductMemberInfoGetRespVO;
import u.chirp.application.product.convert.ChirpProductMemberConvert;
import u.chirp.application.product.dal.dataobject.ChirpProductMemberDO;
import u.chirp.application.product.service.IChirpProductMemberService;
import u.chirp.application.product.service.IChirpProductService;

import java.util.Optional;

/**
 *  @folder app/产品会员
 * @author xiaou
 * @date 2024/11/23
 */
@RequestMapping("product/member")
@RestController
public class ChirpProductMemberController {

    @Resource
    private IChirpProductMemberService chirpProductMemberService;

    @Resource
    private IChirpProductService chirpProductService;

    /**
     * 登录人产品信息
     * @param productCode
     * @return
     */
    @GetMapping("info")
    public R<ChirpProductMemberInfoGetRespVO> info(String productCode) {
        Long productId = chirpProductService.getProductIdByCode(productCode);
        ChirpProductMemberInfoGetRespVO respVO = Optional.ofNullable(chirpProductMemberService.getOne(Wrappers.lambdaQuery(ChirpProductMemberDO.class)
                        .eq(ChirpProductMemberDO::getMemberId, StpUtil.getLoginIdAsLong())
                        .eq(ChirpProductMemberDO::getProductId, productId)
                        .last("limit 1")))
                .map(ChirpProductMemberConvert.INSTANCE::convertProductMemberInfoGetRespVO)
                .orElseGet(() -> {
                    ChirpProductMemberInfoGetRespVO productMemberDO = new ChirpProductMemberInfoGetRespVO();
                    productMemberDO.setMemberId(StpUtil.getLoginIdAsLong());
                    productMemberDO.setProblemCount(0L);
                    productMemberDO.setThumbsUpCount(0L);
                    return productMemberDO;
                });
        return R.success(respVO);
    }

}
