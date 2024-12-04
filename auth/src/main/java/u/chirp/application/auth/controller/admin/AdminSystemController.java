package u.chirp.application.auth.controller.admin;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import u.boot.start.common.pojo.R;
import u.boot.start.common.utils.FilePathUtils;
import u.chirp.application.api.mumber.ChirpMemberApi;
import u.chirp.application.auth.controller.admin.vo.SystemInitReqVo;
import u.chirp.application.auth.controller.admin.vo.SystemAdminLoginReqVO;
import u.chirp.application.auth.service.IAdminUserService;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @folder admin/系统初始化
 * @author xiaou
 * @date 2024/11/19
 */
@RestController
@RequestMapping("/system")
@Slf4j
@RequiredArgsConstructor
public class AdminSystemController {

    @Resource
    private final IAdminUserService adminUserService;


    @Resource
    private final ChirpMemberApi chirpMemberApi;

    /**
     * 登录超级管理员
     * @param reqVo
     * @return
     */
    @PostMapping("login")
    public R<String> login(@RequestBody SystemAdminLoginReqVO reqVo) {
        String token = adminUserService.login(reqVo, getIdentity());
        return R.success(token);
    }

    /**
     * 初始化超级管理员
     * @tags v1.0.0
     */
    @PostMapping("init")
    public synchronized R<Boolean> systemInit(@RequestBody @Validated SystemInitReqVo reqVo)
            throws URISyntaxException, IOException {

        File data = FilePathUtils.getData();
        File initFile = new File(data, "init.lock");
        if (initFile.exists()) {
            return R.success(false);
        }

        if (!getIdentity().equals(reqVo.getIdentityCode())) {
            return R.success(false);
        }
        adminUserService.adminAccountInit(reqVo);
        return R.success(true);
    }

    private String getIdentity() {
        String text = "";
        try {
            File data = FilePathUtils.getData();
            File identityFile = new File(data, "init.identity");
            text = Files.readLines(identityFile, Charsets.UTF_8).get(0);
        }catch (Exception e) {
            return "";
        }

        Pattern pattern = Pattern.compile("\\[(.*)\\]");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            return "";
        }
        return matcher.group(1);
    }
}
