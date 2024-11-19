package u.chirp.application;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaou
 * @date 2024/11/5
 */
@SpringBootApplication
@MapperScan("u.chirp.application.*.dal.mysql")
public class ChirpApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChirpApplication.class, args);
    }
}
