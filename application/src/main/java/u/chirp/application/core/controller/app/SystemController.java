package u.chirp.application.core.controller.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.core.controller.app.vo.SystemInfoGetRespVO;

import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @folder app/系统信息
 * @author xiaou
 * @date 2024/11/18
 */
@RestController
@RequestMapping("system")
public class SystemController {

    /**
     * 系统基本信息
     * @tags v1.0.0
     */
    @GetMapping("info")
    public R<SystemInfoGetRespVO> getSoftwareInfo() {
        URL jarUrl = SystemController.class.getProtectionDomain().getCodeSource().getLocation();
        SystemInfoGetRespVO respVO = new SystemInfoGetRespVO();
        try(JarFile jarFile = new JarFile(jarUrl.getPath())) {
            Manifest manifest = jarFile.getManifest();
            Attributes mainAttributes = manifest.getMainAttributes();
            respVO.setVersion(mainAttributes.getValue("version"));
        }catch (Exception ignored) {
            respVO.setVersion("开发版本");
        }
        return R.success(respVO);
    }
}
