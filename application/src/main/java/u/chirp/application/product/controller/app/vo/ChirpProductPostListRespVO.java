package u.chirp.application.product.controller.app.vo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/22
 */
@Data
public class ChirpProductPostListRespVO {
    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 帖子标题
     */
    private String postTitle;

    /**
     * 原始 html 内容
     */
    private String postRawHtml;

    /**
     * 是否秒评
     */
    private Boolean postGood;

    /**
     * 点赞数
     */
    private Long postThumbsUpCount;

    /**
     * 收藏数
     */
    private Long postCollectCount;

    /**
     * 是否置顶
     */
    private Boolean postTop;

    /**
     * 帖子类型
     */
    private Integer postType;

    /**
     * 帖子处理进度
     */
    private Integer postHandleProgress;
}
