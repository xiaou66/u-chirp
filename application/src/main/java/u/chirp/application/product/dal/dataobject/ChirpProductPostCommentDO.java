package u.chirp.application.product.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Data
@TableName("chirp_product_post_comment")
public class ChirpProductPostCommentDO extends BaseDO<ChirpProductPostCommentDO> {
    /**
     * 评论 id
     */
    @TableField("comment_id")
    private Long commentId;

    /**
     * 回复帖子 id
     */
    @TableField("post_id")
    private Long postId;

    /**
     * 被回复的回复 id，直接回复帖子是 0
     */
    @TableField("parent_comment_id")
    private Long parentCommentId;

    /**
     * 第一次回复的 id
     */
    @TableField("reply_comment_id")
    private Long replyCommentId;

    /**
     * 回复原始 html 内容
     */
    @TableField("comment_raw_html")
    private String commentRawHtml;

    /**
     * 回复置顶
     */
    @TableField("comment_top")
    private Boolean commentTop;

    /**
     * 点赞数
     */
    @TableField("comment_thumbs_up_count")
    private Long commentThumbsUpCount;
}
