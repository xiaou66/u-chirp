package u.chirp.application.product.controller.app.vo;

import cn.hutool.core.io.FileMagicNumber;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberBaseInfoVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/22
 */
@Data
public class ChirpProductPostCommentItemVO {
    /**
     * 评论 id
     */
    private Long commentId;

    /**
     * 回复帖子 id
     */
    private Long postId;

    /**
     * 被回复的回复 id，直接回复帖子是 0
     */
    private Long parentCommentId;

    /**
     * 回复原始 html 内容
     */
    private String commentRawHtml;

    /**
     * 回复置顶
     */
    private Boolean commentTop;

    /**
     * 点赞数
     */
    private Long commentThumbsUpCount;

    /**
     * 文件列表
     */
    private List<FileUrlVO> fileList;

    /**
     * 评论者信息
     */
    private ChirpMemberBaseInfoVO commenterInfo;

    /**
     * 被评论者信息, 评论帖子不返回
     */
    private ChirpMemberBaseInfoVO beCommenterInfo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 评论数量
     */
    private Long commentChildrenCount;

    /**
     * 下一级评论
     */
    private List<ChirpProductPostCommentItemVO> children;
}
