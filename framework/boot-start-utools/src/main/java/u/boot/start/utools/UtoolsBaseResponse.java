package u.boot.start.utools;

import io.github.xiaou66.sdk.BaseResponse;
import lombok.Data;

import java.util.Objects;

/**
 * @author xiaou
 * @date 2024/2/22
 */
@Data
public class UtoolsBaseResponse<T> implements BaseResponse {
    /**
     * 描述
     */
    private String message;
    /**
     * 签名
     */
    private String sign;
    /**
     * 原始数据
     */
    private String original;
    /**
     * 数据
     */
    private T resource;
    /**
     * 是否成功
     */
    public boolean requestSuccess() {
        return Objects.isNull(message);
    }
}
