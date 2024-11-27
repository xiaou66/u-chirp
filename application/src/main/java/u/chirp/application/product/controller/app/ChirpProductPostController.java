package u.chirp.application.product.controller.app;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import u.boot.start.common.pojo.PageResult;
import u.boot.start.common.pojo.R;
import u.boot.start.common.pojo.RollResult;
import u.chirp.application.product.controller.app.vo.*;
import u.chirp.application.product.convert.ChirpProductPostConvert;
import u.chirp.application.product.service.IChirpProductMemberService;
import u.chirp.application.product.service.IChirpProductPostService;
import u.chirp.application.product.service.IChirpProductService;
import u.chirp.application.product.service.bo.AppProductPostListBO;
import u.chirp.application.product.service.bo.ChirpProductPostListBO;
import u.chirp.application.product.service.bo.ProductPostBaseInfoBO;

import java.util.List;
import java.util.Objects;

/**
 * @folder app/帖子
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
    public R<RollResult<ChirpProductPostListRespVO>> list(AppProductPostListGetReqVO reqVo) {
        AppProductPostListBO appProductPostListBO = chirpProductPostService.payloadQueryParam(reqVo);
        List<Long> ids = chirpProductPostService.searchIdList(appProductPostListBO);
        List<ChirpProductPostListBO> res = chirpProductPostService.payloadResult(ids);
        List<ChirpProductPostListRespVO> respList = ChirpProductPostConvert.INSTANCE
                .toChirpProductPostListRespDO(res);
        return R.success(new RollResult<>(respList, ids.get(ids.size() - 1)));
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
     * 关注
     * @tags v1.0.0
     * @param reqVo
     */
    @PostMapping("follow")
    public R<ProductPostBaseInfoBO> follow(@Validated @RequestBody AppProductPostFollowReqVO reqVo) {
        Long productId = chirpProductService.getProductIdByCode(reqVo.getProductCode());
        chirpProductPostService.follow(reqVo, productId);
        ProductPostBaseInfoBO productPost = chirpProductPostService.getPostBaseInfo(reqVo.getPostId());
        return R.success(productPost);
    }
}
