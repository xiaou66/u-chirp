package u.chirp.application.mumber.controller.admin;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import u.boot.start.common.exception.enums.GlobalErrorCodeConstants;
import u.boot.start.common.exception.util.ServiceExceptionUtil;
import u.boot.start.common.pojo.PageParam;
import u.boot.start.common.pojo.PageResult;
import u.boot.start.common.pojo.R;
import u.chirp.application.mumber.controller.admin.vo.SocialAccountListGetRespVO;
import u.chirp.application.mumber.controller.admin.vo.SocialAccountSaveReqVO;
import u.chirp.application.mumber.convert.ChirpSocialAccountConvert;
import u.chirp.application.mumber.dal.dataobject.ChirpSocialAccountDO;
import u.chirp.application.mumber.service.IChirpSocialAccountService;

import java.util.List;

/**
 * @folder app/应用客户端
 * @author xiaou
 * @date 2024/11/21
 */
@RequestMapping("social")
@RestController
@Validated
public class AdminChirpSocialAccountController {

    @Resource
    private IChirpSocialAccountService chirpSocialAccountService;

    /**
     * 新增应用客户端
     * @tags v1.0.0
     */
    @PostMapping("/save")
    public R<Boolean> save(@RequestBody @Validated SocialAccountSaveReqVO reqVo) {
        ChirpSocialAccountDO socialAccount = ChirpSocialAccountConvert.INSTANCE.convert(reqVo);
        chirpSocialAccountService.saveOrUpdate(socialAccount);
        return R.success(true);
    }

    /**
     * 应用客户端列表
     * @tags v1.0.0
     */
    @GetMapping("list")
    public R<PageResult<SocialAccountListGetRespVO>> list(@Validated PageParam pageParam) {
        IPage<ChirpSocialAccountDO> page = new Page<>(pageParam.getPageNo(), pageParam.getPageSize());
        List<ChirpSocialAccountDO> list = chirpSocialAccountService.list(page, Wrappers.lambdaQuery(ChirpSocialAccountDO.class)
                .eq(ChirpSocialAccountDO::getCreator, StpUtil.getLoginIdAsLong()));
        List<SocialAccountListGetRespVO> respVOList = ChirpSocialAccountConvert.INSTANCE.convertSocialAccountListGetRespVO(list);
        return R.success(new PageResult<SocialAccountListGetRespVO>(respVOList, page.getTotal()));
    }

    /**
     * 查看应用客户端密钥
     * @tags v1.0.0
     * @return
     */
    @PostMapping("getSocialClientSecret")
    public R<String> socialClientSecret(@Validated @RequestBody SocialClientSecretGetReqVO reqVo) {

        String socialClientSecret = chirpSocialAccountService.getOneOpt(Wrappers.lambdaQuery(ChirpSocialAccountDO.class)
                        .select(ChirpSocialAccountDO::getSocialClientSecret)
                        .eq(ChirpSocialAccountDO::getSocialId, reqVo.getSocialId())
                        .eq(ChirpSocialAccountDO::getCreator, StpUtil.getLoginIdAsLong()))
                .map(ChirpSocialAccountDO::getSocialClientSecret)
                .orElseThrow(() -> ServiceExceptionUtil.exception(GlobalErrorCodeConstants.FORBIDDEN));

        return R.success(socialClientSecret);
    }

    /**
     * 删除应用客户端
     * @param reqVo
     * @return
     */
    @PostMapping("delete")
    public R<Boolean> delete(@Validated @RequestBody SocialAccountDeleteReqVO reqVo) {

        boolean remove = chirpSocialAccountService.remove(Wrappers.lambdaQuery(ChirpSocialAccountDO.class)
                .eq(ChirpSocialAccountDO::getSocialId, reqVo.getSocialId())
                .eq(ChirpSocialAccountDO::getCreator, StpUtil.getLoginIdAsLong()));

        return R.success(remove);
    }
}
