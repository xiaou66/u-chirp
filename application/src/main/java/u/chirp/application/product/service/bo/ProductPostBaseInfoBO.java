package u.chirp.application.product.service.bo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Data
public class ProductPostBaseInfoBO {

    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 关联产品 id
     */
    private Long productId;

    /**
     * 点赞数
     */
    private Long postThumbsUpCount;

    /**
     * 收藏数
     */
    private Long postFollowCount;

    /**
     * 是否置顶
     */
    private Boolean postTop;

    /**
     * 帖子类型
     */
    private String postType;

    /**
     * 帖子处理进度
     */
    private Integer postHandleProgress;


    /**
     * 创建人
     */
    private Long creator;
}
