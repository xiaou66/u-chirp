package u.boot.start.common.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author xiaou
 * @date 2024/11/19
 */
public class FilePathUtils {

    /**
     * 获取 jar 包路径
     * @return
     * @throws URISyntaxException
     */
    public static File getJarPath() throws URISyntaxException {
        URL jarUrl = FilePathUtils.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = jarUrl.toURI().getPath();
        return new File(jarPath);
    }

    /**
     * 获取数据存储位置
     * @return
     * @throws URISyntaxException
     */
    public static File getData() throws URISyntaxException {
        String jarDir = FilePathUtils.getJarPath().getParent();
        return new File(jarDir, "data");
    }

    /**
     * 获取数据存储位置 <br/>
     * 尽可能使用 {@link FilePathUtils#getData()} <br/>
     * 这个会将异常处理成运行时异常
     */
    public static File getData0()  {
        try {
            return FilePathUtils.getData();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
