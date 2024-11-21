package u.chirp.application.product;

import u.boot.start.common.exception.ErrorCode;

public interface ProductErrorCodeConstants {
    /**
     * 帖子不存在
     */
    ErrorCode PRODUCT_POST_EXISTENCE = new ErrorCode(2_001_001, "帖子不存在");
    ErrorCode PRODUCT_EXISTENCE = new ErrorCode(2_002_001, "产品不存在");
}
