package u.chirp.application.mumber.controller.app;

import cn.dev33.satoken.annotation.SaIgnore;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberAppLoginReqVO;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberLoginReqVO;
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
     * 产品应用登录
     * @tags v1.0,0
     */
    @PostMapping("appLogin")
    @SaIgnore
    private R<String> appLogin(@Validated @RequestBody ChirpMemberAppLoginReqVO reqVo) {
        ChirpSocialAccountDO chirpSocialAccount = chirpSocialAccountService.getByClientId(reqVo.getClientId());
        MemberLoginSuccessInfoBO successInfo = new MemberLoginSuccessInfoBO();
        if (SocialType.SELF_BUILD.getValue().equals(chirpSocialAccount.getSocialType())) {
            successInfo = chirpMemberAccountService.thirdPartyLogin(reqVo, chirpSocialAccount);
        }

        chirpMemberService.loginAfter(successInfo.getMemberId());
        return R.success(successInfo.getToken());
    }
}
