package u.chirp.application.product.controller.app;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.service.IChirpProductMemberService;
import u.chirp.application.product.service.IChirpProductPostService;

import java.util.Objects;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@RequestMapping("product/member")
@RestController
public class ChirpProductMemberController {

    @Resource
    private IChirpProductPostService chirpProductPostService;

    @Resource
    private IChirpProductMemberService chirpProductMemberService;

    /**
     * 创建帖子
     * @return
     * @tags v1.1.0
     */
    @PostMapping("/savePost")
    @SaCheckLogin
    @Transactional
    public R<Long> savePost(@Validated @RequestBody AppProductPostSaveReqVO reqVo) {
        Long postId = chirpProductPostService.savePost(reqVo);

        if (Objects.isNull(reqVo.getPostId())) {
            chirpProductMemberService.addPostCount(StpUtil.getLoginIdAsLong(), reqVo.getProductId());
        }

        return R.success(postId);
    }
}
