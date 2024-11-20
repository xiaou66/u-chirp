package u.chirp.application.product.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Data
@TableName("chirp_product_post")
public class ChirpProductPostDO extends BaseDO<ChirpProductPostDO> {
    /**
     * 帖子 id
     */
    @TableId(value = "post_id")
    private Long postId;

    /**
     * 关联产品 id
     */
    private Long productId;

    /**
     * 帖子标题
     */
    @TableField("post_title")
    private String postTitle;

    /**
     * 原始内容 Delta 格式
     */
    @TableField("post_raw_content")
    private String postRawContent;

    /**
     * 原始 html 内容
     */
    @TableField("post_raw_html")
    private String postRawHtml;

    /**
     * 是否秒评
     */
    @TableField("post_good")
    private Boolean postGood;

    /**
     * 基础热点值
     */
    @TableField("post_hot")
    private Integer postHot;

    /**
     * 点赞数
     */
    @TableField("post_thumbs_up_count")
    private Long postThumbsUpCount;

    /**
     * 收藏数
     */
    @TableField("post_collect_count")
    private Long postCollectCount;

    /**
     * 是否置顶
     */
    @TableField("post_top")
    private Boolean postTop;

    /**
     * 帖子类型
     */
    @TableField("post_type")
    private Integer postType;

    /**
     * 帖子处理进度
     */
    @TableField("post_handle_progress")
    private Integer postHandleProgress;
}
