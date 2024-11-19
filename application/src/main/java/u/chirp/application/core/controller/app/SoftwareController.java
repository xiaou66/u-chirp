package u.chirp.application.core.controller.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.core.controller.app.vo.SoftwareInfoGetRespVO;

import java.net.URL;
import java.util.jar.Attributes;
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
        SoftwareInfoGetRespVO respVO = new SoftwareInfoGetRespVO();
        try {
            JarFile jarFile = new JarFile(jarUrl.getPath());
            Manifest manifest = jarFile.getManifest();
            Attributes mainAttributes = manifest.getMainAttributes();
            respVO.setVersion(mainAttributes.getValue("version"));
        }catch (Exception ignored) {
            respVO.setVersion("开发版本");
        }
        return R.success(respVO);
    }
}
