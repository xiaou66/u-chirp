package u.boot.start.utools.info;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import u.boot.start.utools.UtoolsBaseRequest;
import u.boot.start.utools.UtoolsBaseResponse;

/**
 * @author xiaou
 * @date 2024/2/22
 */
@Data
public class UtoolsUserInfoRequest extends UtoolsBaseRequest<UtoolsBaseResponse<UtoolsUserInfoResponse>> {
    @JsonProperty("plugin_id")
    private String pluginId;
    @JsonProperty("access_token")
    private String accessToken;

    @Override
    public String getRequestUrl() {
        return "https://open.u-tools.cn/baseinfo";
    }
}
