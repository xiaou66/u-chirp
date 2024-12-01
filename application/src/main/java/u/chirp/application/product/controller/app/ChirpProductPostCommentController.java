package u.chirp.application.product.controller.app;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import u.boot.start.common.exception.NoDataException;
import u.boot.start.common.pojo.R;
import u.boot.start.common.pojo.RollResult;
import u.chirp.application.core.filecenter.service.IChirpFileManagerService;
import u.chirp.application.product.constant.ProductFileManagerCodeConstant;
import u.chirp.application.product.controller.app.vo.*;
import u.chirp.application.product.convert.ChirpProductPostCommentConvert;
import u.chirp.application.product.dal.dataobject.ChirpProductPostCommentDO;
import u.chirp.application.product.service.IChirpProductPostCommentService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @folder app/帖子回复
 * @author xiaou
 * @date 2024/11/20
 */
@RequestMapping("product/post/comment")
@RestController
public class ChirpProductPostCommentController {

    @Resource
    private IChirpProductPostCommentService chirpProductPostCommentService;

    @Resource
    private IChirpFileManagerService chirpFileManagerService;

    /**
     * 发布评论
     * @tags v1.0,0
     */
    @PostMapping("/create")
    public R<Long> create(@RequestBody @Validated ChirpProductPostCommentCreateReqVO reqVo) {
        ChirpProductPostCommentDO productPostComment = ChirpProductPostCommentConvert.INSTANCE.convert(reqVo);
        chirpProductPostCommentService.create(productPostComment);
        if (CollUtil.isNotEmpty(reqVo.getFileIds())) {
            chirpFileManagerService.batchSaveRefFileIds(ProductFileManagerCodeConstant.POST_COMMENT_FILE,
                    productPostComment.getCommentId(),
                    reqVo.getFileIds());
        }
        // 更新评论统计
        chirpProductPostCommentService.updateTotalComment(reqVo.getParentCommentId());
        return R.success(productPostComment.getCommentId());
    }

    /**
     * 获取帖子评论
     * @return
     * @tags v1.0.0
     */
    @GetMapping("/getList")
    public R<ChirpProductPostCommentListRespVO> getList(@Validated ChirpProductPostCommentListGetReqVO reqVo) {
        List<Long> commentIds = null;
        try {
            commentIds = chirpProductPostCommentService.searchIdList(reqVo);
        } catch (NoDataException e) {
            ChirpProductPostCommentListRespVO respVO = new ChirpProductPostCommentListRespVO();
            respVO.setList(Collections.emptyList());
            respVO.setNext(null);
            return R.success(respVO);
        }

        final List<Long> commentIdsFinal = commentIds;
        ChirpProductPostCommentListRespVO respVO = chirpProductPostCommentService.payloadResult(reqVo, commentIdsFinal);
        List<ChirpProductPostCommentItemVO> list = respVO.getList().stream()
                .sorted((a, b) ->
                        commentIdsFinal.indexOf(a.getCommentId() - commentIdsFinal.indexOf(b.getCommentId())))
                .toList();
        respVO.setList(list);
        respVO.setNext(respVO.getList().get(list.size() - 1).getCommentId());
        return R.success(respVO);
    }


    /**
     * 获取子评论列表
     * @tags v1.0.0
     */
    @GetMapping("getChildrenList")
    public R<ChirpProductPostCommentListRespVO> getChildrenList(@Validated ChirpProductPostCommentChildrenListGetReqVO reqVo) {
        List<Long> commentIds = null;
        try {
            commentIds = chirpProductPostCommentService.getChildrenIdsList(reqVo);
        } catch (NoDataException e) {
            ChirpProductPostCommentListRespVO respVO = new ChirpProductPostCommentListRespVO();
            respVO.setList(Collections.emptyList());
            respVO.setNext(null);
            return R.success(respVO);
        }
        final List<Long> commentIdsFinal = commentIds;
        ChirpProductPostCommentListRespVO respVO = chirpProductPostCommentService.payloadResult(null, commentIds);
        List<ChirpProductPostCommentItemVO> list = respVO.getList().stream()
                .sorted(Comparator.comparingInt(a -> commentIdsFinal.indexOf(a.getCommentId())))
                .toList();
        respVO.setList(list);
        return R.success(respVO);

    }
}
