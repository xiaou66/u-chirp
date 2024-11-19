package u.chirp.application.core.controller.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.core.controller.app.vo.SoftwareInfoGetRespVO;

import java.net.URL;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @folder app/基本信息
 * @author xiaou
 * @date 2024/11/18
 */
@RestController
@RequestMapping("software")
public class SoftwareController {

    /**
     * 软件基本信息
     * @tags v1.0.0
     */
    @GetMapping("info")
    public R<SoftwareInfoGetRespVO> getSoftwareInfo() {
        URL jarUrl = SoftwareController.class.getProtectionDomain().getCodeSource().getLocation();
        try {
            JarFile jarFile = new JarFile(jarUrl.getPath());
            Manifest manifest = jarFile.getManifest();
            System.out.println(manifest.getAttributes("version"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return R.success(null);
    }
}
