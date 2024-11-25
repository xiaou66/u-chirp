package u.chirp.application.product.service.bo;

import lombok.Data;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/22
 */
@Data
public class ChirpProductPostListBO {
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
     * 文件列表
     */
    private List<FileUrlVO> fileList;
}
