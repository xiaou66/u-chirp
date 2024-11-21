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
import u.chirp.application.mumber.service.IChirpMemberService;

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

    /**
     * 会员登录
     * @tags v1.0,0
     */
    @PostMapping("login")
    @SaIgnore
    public R<String> login(@Validated @RequestBody ChirpMemberLoginReqVO reqVo) {
        String token = chirpMemberService.login(reqVo);
        return R.success(token);
    }

    /**
     * 应用登录
     * @tags v1.0,0
     */
    @PostMapping("appLogin")
    @SaIgnore
    private R<String> appLogin(@Validated @RequestBody ChirpMemberAppLoginReqVO reqVo) {
        String token = chirpMemberService.appLogin(reqVo);
        return R.success(token);
    }
}
