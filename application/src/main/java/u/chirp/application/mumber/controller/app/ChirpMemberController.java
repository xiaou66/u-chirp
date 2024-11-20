package u.chirp.application.mumber.controller.app;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberLoginReqVO;
import u.chirp.application.mumber.service.IChirpMemberService;

/**
 * @folder app/会员
 * @author xiaou
 * @date 2024/11/17
 */
@RequestMapping("member")
@RestController
public class ChirpMemberController {

    @Resource
    private IChirpMemberService chirpMemberService;

    /**
     * 会员登录
     * @tags v1.0,0
     */
    @PostMapping("login")
    public R<String> login(@RequestBody ChirpMemberLoginReqVO reqVo) {
        String token = chirpMemberService.login(reqVo);
        return R.success(token);
    }

}
