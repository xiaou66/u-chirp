package u.boot.start.utools;

import io.github.xiaou66.sdk.client.IClient;
import io.github.xiaou66.sdk.util.ReflectUtils;
import u.boot.start.common.exception.enums.GlobalErrorCodeConstants;
import u.boot.start.utools.utils.UtoolsSignUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static u.boot.start.common.exception.util.ServiceExceptionUtil.exception;


/**
 * @author xiaou
 * @date 2024/2/23
 */
public class UtoolsClient implements IClient<UtoolsBaseRequest<UtoolsBaseResponse<Object>>, UtoolsBaseResponse<Object>> {

    public static final Map<String, UtoolsClient> CLIENTS = new ConcurrentHashMap<>();

    /**
     * 密钥
     */
    private final String secret;

    public UtoolsClient(String secret) {
        this.secret = secret;
    }


    @Override
    public void beforeRequestParams(UtoolsBaseRequest<UtoolsBaseResponse<Object>> request) {
        request.setTimestamp(System.currentTimeMillis() / 1000);
        request.setSign(UtoolsSignUtils.sign(ReflectUtils.extractFields(request), secret));;

    }

    @Override
    public void beforeResponse(UtoolsBaseResponse<Object> res, String json) {
        res.setOriginal(json);
    }

    public static UtoolsClient getUtoolsClient(String appId, String appSecret) {
        if (appId == null || appSecret == null) {
            throw exception(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR);
        }

        UtoolsClient utoolsClient = CLIENTS.get(appId);
        if (Objects.nonNull(utoolsClient)) {
            return utoolsClient;
        }

        synchronized (UtoolsClient.class) {
            return initUtoolsClient(appId, appSecret);
        }

    }

    private static UtoolsClient initUtoolsClient(String appId, String appSecret) {
        return CLIENTS.putIfAbsent(appId, new UtoolsClient(appSecret));
    }
}
