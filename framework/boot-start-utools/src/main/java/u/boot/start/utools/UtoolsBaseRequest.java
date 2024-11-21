package u.boot.start.utools;


import io.github.xiaou66.sdk.BaseRequest;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/2/23
 */
@Data
public class UtoolsBaseRequest<R> implements BaseRequest<R> {
    private String sign;
    private Long timestamp;
}
