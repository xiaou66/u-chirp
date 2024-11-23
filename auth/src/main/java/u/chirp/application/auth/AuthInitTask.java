package u.chirp.application.auth;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import u.boot.start.common.utils.FilePathUtils;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.UUID;

/**
 * 实现
 * @author xiaou
 * @date 2024/11/19
 */
@Component
@Slf4j
public class AuthInitTask implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 获取当前类的 JAR 文件路径
        File data = FilePathUtils.getData();
        File initFile = new File(data, "init.identity");
        if (initFile.exists()) {
            return;
        }

        if (!data.exists()) {
            boolean mkdir = data.mkdir();
            if (!mkdir) {
                throw new Exception("Unable to create data folder, path:" + data.getPath());
            }
        }

        log.info("identityCode generate cat {}", initFile.getPath());

        File identityFile = new File(data, "init.identity");
        // 不检查文件是否存在，直接创建并覆盖
        try (FileWriter writer = new FileWriter(identityFile, false)) {
            String text = String.format("identityCode is [%s]\n",
                    UUID.randomUUID().toString().replace("-", ""));
            writer.write(text);
            writer.flush();
        }

    }
}
