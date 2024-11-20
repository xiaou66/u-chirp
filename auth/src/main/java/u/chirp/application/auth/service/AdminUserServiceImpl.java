package u.chirp.application.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import u.boot.start.common.exception.ServiceException;
import u.boot.start.common.exception.enums.GlobalErrorCodeConstants;
import u.boot.start.common.exception.util.ServiceExceptionUtil;
import u.boot.start.common.utils.FilePathUtils;
import u.chirp.application.auth.AuthConstant;
import u.chirp.application.auth.controller.admin.vo.SystemAdminLoginReqVO;
import u.chirp.application.auth.controller.admin.vo.SystemInitReqVo;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author xiaou
 * @date 2024/11/19
 */
@Slf4j
@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Override
    public void adminAccountInit(SystemInitReqVo reqVo) {
        try {
            File data = FilePathUtils.getData();
            File initFile = new File(data, "init.lock");
            String username = reqVo.getUsername();
            String password = reqVo.getPassword();
            String encrypt = accountEncrypt(username, password, reqVo.getIdentityCode());
            try (FileWriter writer = new FileWriter(initFile, false)) {
                writer.write(encrypt);
                writer.flush();
            }
        }catch (Exception e) {
            log.error("adminAccountInit 出现异常", e);
        }
    }

    @Override
    public String login(SystemAdminLoginReqVO reqVo, String identity) {
        try {
            String userInput = accountEncrypt(reqVo.getUsername(), reqVo.getPassword(), identity);
            File data = FilePathUtils.getData();
            File initFile = new File(data, "init.lock");
            String pwd = Files.readLines(initFile, Charsets.UTF_8).get(0);
            if (!userInput.equals(pwd)) {
                throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.BAD_REQUEST);
            }
            StpUtil.login(AuthConstant.ADMIN_USER_ID);
            return StpUtil.getTokenInfo().getTokenValue();
        }catch (Exception e) {
            e.printStackTrace();
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.BAD_REQUEST);
        }
    }


    private String accountEncrypt(String username, String password, String identityCode) throws Exception {
        String usernameEncrypt = encrypt(username, identityCode.substring(0, 17).getBytes(StandardCharsets.UTF_8));
        String passwordEncrypt = encrypt(password, identityCode.substring(17, 32).getBytes(StandardCharsets.UTF_8));
        return Hashing.hmacSha512((usernameEncrypt).getBytes(StandardCharsets.UTF_8))
                .hashString(passwordEncrypt, StandardCharsets.UTF_8)
                .toString();
    }
    /**
     * 使用 PBKDF2 加密字符串，并加盐
     * @param input
     * @param salt
     * @return
     * @throws Exception
     */
    private static String encrypt(String input, byte[] salt) throws Exception {
        int iterations = 10000; // 迭代次数
        int keyLength = 256; // 密钥长度

        PBEKeySpec spec = new PBEKeySpec(input.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(hash);
    }
}
