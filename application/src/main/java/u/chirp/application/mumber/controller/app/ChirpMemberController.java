package u.chirp.application.mumber.controller.app;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import u.boot.start.common.exception.enums.GlobalErrorCodeConstants;
import u.boot.start.common.exception.util.ServiceExceptionUtil;
import u.boot.start.common.pojo.R;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberThirdPartyLoginReqVO;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberLoginReqVO;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberUtoolsLoginReqVO;
import u.chirp.application.mumber.controller.app.vo.MemberInfoGetRespVO;
import u.chirp.application.mumber.convert.ChirpMemberConvert;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;
import u.chirp.application.mumber.dal.dataobject.ChirpSocialAccountDO;
import u.chirp.application.mumber.enums.SocialType;
import u.chirp.application.mumber.service.IChirpMemberAccountService;
import u.chirp.application.mumber.service.IChirpMemberService;
import u.chirp.application.mumber.service.IChirpSocialAccountService;
import u.chirp.application.mumber.service.bo.MemberLoginSuccessInfoBO;

/**
 * @folder app/会员
 * @author xiaou
 * @date 2024/11/17
 */
@RequestMapping("member")
@RestController
@Validated
public class ChirpMemberController {

    @Resource
    private IChirpMemberService chirpMemberService;


    @Resource
    private IChirpMemberAccountService chirpMemberAccountService;

    @Resource
    private IChirpSocialAccountService chirpSocialAccountService;

    /**
     * 会员登录
     * @tags v1.0,0
     */
    @PostMapping("login")
    @SaIgnore
    public R<String> login(@Validated @RequestBody ChirpMemberLoginReqVO reqVo) {
        MemberLoginSuccessInfoBO successInfo = chirpMemberService.login(reqVo);
        chirpMemberService.loginAfter(successInfo.getMemberId());
        return R.success(successInfo.getToken());
    }

    /**
     * 第三方应用登录
     * @tags v1.0,0
     */
    @PostMapping("thirdPartyLogin")
    @SaIgnore
    private R<String> thirdPartyLogin(@Validated @RequestBody ChirpMemberThirdPartyLoginReqVO reqVo) {
        ChirpSocialAccountDO chirpSocialAccount = chirpSocialAccountService.getByClientId(reqVo.getClientId());
        if (!SocialType.SELF_BUILD.getValue().equals(chirpSocialAccount.getSocialType())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.BAD_REQUEST,
                    "clientId 对应客户端类型错误请掉对应接口");
        }

        MemberLoginSuccessInfoBO successInfo = chirpMemberAccountService
                .thirdPartyLogin(reqVo, chirpSocialAccount);
        chirpMemberService.loginAfter(successInfo.getMemberId());
        return R.success(successInfo.getToken());
    }

    /**
     * utools 登录
     * @param reqVo
     * @return
     */
    @PostMapping("utoolsLogin")
    @SaIgnore
    private R<String> utoolsLogin(@Validated @RequestBody ChirpMemberUtoolsLoginReqVO reqVo) {
        ChirpSocialAccountDO chirpSocialAccount = chirpSocialAccountService.getByClientId(reqVo.getClientId());
        if (!SocialType.UTOOLS.getValue().equals(chirpSocialAccount.getSocialType())) {
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.BAD_REQUEST,
                    "clientId 对应客户端类型错误请掉对应接口");
        }

        MemberLoginSuccessInfoBO successInfo = chirpMemberAccountService
                .utoolsLogin(reqVo, chirpSocialAccount);
        chirpMemberService.loginAfter(successInfo.getMemberId());
        return R.success(successInfo.getToken());
    }


    /**
     * 当前登录会员信息
     * @tags v1.0.0
     */
    @GetMapping("/info")
    public R<MemberInfoGetRespVO> memberInfo() {
        ChirpMemberDO chirpMember = chirpMemberService.getById(StpUtil.getLoginIdAsLong());
        MemberInfoGetRespVO respVo = ChirpMemberConvert.INSTANCE.toProductMemberInfoGetReqVO(chirpMember);
        return R.success(respVo);
    }
}
