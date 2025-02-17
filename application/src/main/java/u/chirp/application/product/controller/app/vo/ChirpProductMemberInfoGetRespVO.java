package u.chirp.application.product.controller.app.vo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/23
 */
@Data
public class ChirpProductMemberInfoGetRespVO {

    /**
     * 会员 id
     */
    private Long memberId;

    /**
     * 问题数
     */
    private Long problemCount;

    /**
     * 点赞数
     */
    private Long thumbsUpCount;
}
