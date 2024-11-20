package u.chirp.application.mumber;

import u.boot.start.common.exception.ErrorCode;

/**
 * 会员错误码
 * @author xiaou
 * @date 2024/11/20
 */
public interface MemberErrorCodeConstants {
    /**
     * 登录信息错误
     */
    ErrorCode LOGIN_ERROR = new ErrorCode(1_000_001, "登录信息不正确");
}
