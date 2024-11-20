package u.chirp.application.product.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.product.controller.app.vo.ChirpProductCreateReqVO;
import u.chirp.application.product.service.IChirpProductService;

/**
 * @folder admin/产品管理
 * @author xiaou
 * @date 2024/11/17
 */
@RequestMapping("product")
@RestController
public class AdminChirpProductController {

    @Resource
    private IChirpProductService chirpProductService;


    /**
     * 创建产品
     * @param reqVo
     * @tags v1.0.0
     */
    @RequestMapping("/create")
    public R<ChirpProductCreateRespVO> create(@Validated ChirpProductCreateReqVO reqVo) {

        return R.success(null);
    }
}
