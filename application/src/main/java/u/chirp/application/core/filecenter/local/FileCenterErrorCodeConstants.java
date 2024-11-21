package u.chirp.application.core.filecenter.local;

import u.boot.start.common.exception.ErrorCode;

/**
 * 会员错误码
 * @author xiaou
 * @date 2024/11/20
 */
public interface FileCenterErrorCodeConstants {
    /**
     * 登录信息错误
     */
    ErrorCode FILE_UPLOAD_ERROR = new ErrorCode(3_000_001, "文件上传出现异常");
}
