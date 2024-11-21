package u.chirp.application.product.controller.app;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import u.boot.start.common.pojo.R;
import u.chirp.application.product.controller.app.vo.*;
import u.chirp.application.product.service.IChirpProductMemberService;
import u.chirp.application.product.service.IChirpProductPostService;
import u.chirp.application.product.service.IChirpProductService;
import u.chirp.application.product.service.bo.ProductPostBaseInfoBO;

import java.util.Objects;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@RequestMapping("product/post")
@RestController
public class ChirpProductPostController {

    @Resource
    private IChirpProductPostService chirpProductPostService;

    @Resource
    private IChirpProductMemberService chirpProductMemberService;

    @Resource
    private IChirpProductService chirpProductService;


    /**
     * 帖子列表
     * @param reqVo
     * @return
     */
    @GetMapping("/list")
    public R<String> list(AppProductPostListGetReqVO reqVo) {
        return R.success("");
    }

    /**
     * 创建帖子
     * @return
     * @tags v1.1.0
     */
    @PostMapping("/save")
    @Transactional
    public R<Long> savePost(@Validated @RequestBody AppProductPostSaveReqVO reqVo) {
        Long productId = chirpProductService.getProductIdByCode(reqVo.getProductCode());
        Long postId = chirpProductPostService.savePost(reqVo, productId);
        if (Objects.isNull(reqVo.getPostId())) {
            chirpProductMemberService.addPostCount(StpUtil.getLoginIdAsLong(), productId);
        }

        return R.success(postId);
    }

    /**
     * 点赞/取消点赞
     * @param reqVo
     * @tags v1.0.0
     */
    @PostMapping("thumbsUp")
    public R<ProductPostBaseInfoBO> thumbsUp(@Validated @RequestBody AppProductPostThumbsUpReqVO reqVo) {
        Long productId = chirpProductService.getProductIdByCode(reqVo.getProductCode());
        chirpProductPostService.thumbsUp(reqVo, productId);
        ProductPostBaseInfoBO productPost = chirpProductPostService.getPostBaseInfo(reqVo.getPostId());
        return R.success(productPost);
    }

    /**
     * 收藏
     * @tags v1.0.0
     * @param reqVo
     */
    @PostMapping("collect")
    public R<ProductPostBaseInfoBO> collect(@Validated @RequestBody AppProductPostCollectReqVO reqVo) {
        Long productId = chirpProductService.getProductIdByCode(reqVo.getProductCode());
        chirpProductPostService.collect(reqVo, productId);
        ProductPostBaseInfoBO productPost = chirpProductPostService.getPostBaseInfo(reqVo.getPostId());
        return R.success(productPost);
    }
}
