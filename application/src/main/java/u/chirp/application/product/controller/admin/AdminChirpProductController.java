package u.chirp.application.product.controller.admin;

import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import u.boot.start.common.pojo.R;
import u.chirp.application.product.controller.admin.vo.ChirpProductItemRespVO;
import u.chirp.application.product.controller.admin.vo.ChirpProductCreateReqVO;
import u.chirp.application.product.service.IChirpProductService;

import java.util.List;

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
    @PostMapping("/create")
    public R<Long> create(@Validated @RequestBody ChirpProductCreateReqVO reqVo) {
        Long productId = chirpProductService.create(reqVo);
        return R.success(productId);
    }

    /**
     * 获取产品列表
     * @tags v1.0.0
     */
    @GetMapping("/list")
    public R<List<ChirpProductItemRespVO>> list() {
        List<ChirpProductItemRespVO> list = chirpProductService.productList();
        return R.success(list);
    }
}
