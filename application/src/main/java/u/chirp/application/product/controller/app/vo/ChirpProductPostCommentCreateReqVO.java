package u.chirp.application.product.controller.app.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/30
 */
@Data
public class ChirpProductPostCommentCreateReqVO {

    /**
     * 回复帖子 id
     */
    @NotNull(message = "回复帖子 id 不能为空")
    private Long postId;

    /**
     * 被回复的回复 id，直接回复帖子是 0
     */
    private Long parentCommentId = 0L;

    /**
     * 回复的 id
     */
    private Long replyCommentId;

    /**
     * 回复原始 html 内容
     */
    @NotBlank(message = "回复内容不能为空")
    private String commentRawHtml;

    /**
     * 文件 id
     */
    private List<Long> fileIds;

}
