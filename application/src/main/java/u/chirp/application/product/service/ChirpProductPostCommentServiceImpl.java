package u.chirp.application.product.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import u.boot.start.common.exception.NoDataException;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;
import u.chirp.application.core.filecenter.service.IChirpFileManagerService;
import u.chirp.application.mumber.convert.ChirpMemberConvert;
import u.chirp.application.mumber.service.IChirpMemberService;
import u.chirp.application.mumber.service.bo.ChirpMemberBaseInfoBO;
import u.chirp.application.product.constant.ProductFileManagerCodeConstant;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentChildrenListGetReqVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentItemVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentListGetReqVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentListRespVO;
import u.chirp.application.product.convert.ChirpProductPostCommentConvert;
import u.chirp.application.product.convert.ChirpProductPostConvert;
import u.chirp.application.product.dal.dataobject.ChirpProductPostCommentDO;
import u.chirp.application.product.dal.mysql.ChirpProductPostCommentMapper;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Service
public class ChirpProductPostCommentServiceImpl extends ServiceImpl<ChirpProductPostCommentMapper, ChirpProductPostCommentDO>
        implements IChirpProductPostCommentService {

    @Resource
    private IChirpMemberService chirpMemberService;

    @Resource
    private IChirpFileManagerService chirpFileManagerService;


    @Override
    public Long create(ChirpProductPostCommentDO productPostComment) {
        if (!Objects.equals(productPostComment.getParentCommentId(), 0L)) {
            // 回复的不是帖子而且评论
            ChirpProductPostCommentDO postComment = getOne(Wrappers.lambdaQuery(ChirpProductPostCommentDO.class)
                    .select(ChirpProductPostCommentDO::getCreator,
                            ChirpProductPostCommentDO::getParentCommentId,
                            ChirpProductPostCommentDO::getReplyCommentId)
                    .eq(ChirpProductPostCommentDO::getCommentId, productPostComment.getReplyCommentId())
                    .last("limit 1"));
            if (Objects.isNull(postComment)) {
                // 回复评论已经被删除了
            }

            if (Objects.nonNull(postComment.getReplyCommentId())
                    && !Objects.equals(postComment.getReplyCommentId(), 0L)) {
                // 是评论的回复的回复
                productPostComment.setReplyMemberId(postComment.getCreator());
            }
        }

        baseMapper.insert(productPostComment);

        return productPostComment.getCommentId();
    }

    @Override
    public List<Long> getChildrenIdsList(ChirpProductPostCommentChildrenListGetReqVO reqVo) throws NoDataException {
        List<Long> commentIds = baseMapper.getChildrenIdsList(reqVo);
        if (CollUtil.isEmpty(commentIds)) {
            throw new NoDataException();
        }
        return commentIds;
    }

    @Override
    public void updateTotalComment(Long commentId) {
        Long count = baseMapper.selectCount(Wrappers.lambdaQuery(ChirpProductPostCommentDO.class)
                .eq(ChirpProductPostCommentDO::getParentCommentId, commentId));
        baseMapper.update(Wrappers.lambdaUpdate(ChirpProductPostCommentDO.class)
                .set(ChirpProductPostCommentDO::getCommentChildrenCount, count)
                .eq(ChirpProductPostCommentDO::getCommentId, commentId));
    }

    @Override
    public List<Long> searchIdList(ChirpProductPostCommentListGetReqVO reqVo) throws NoDataException {
        List<Long> commentIds = baseMapper.searchIdList(reqVo);
        if (CollUtil.isEmpty(commentIds)) {
            throw new NoDataException();
        }
        return commentIds;
    }

    @Override
    public ChirpProductPostCommentListRespVO payloadResult(ChirpProductPostCommentListGetReqVO reqVo, List<Long> commentIds) {
        List<ChirpProductPostCommentDO> commentList = baseMapper.selectByIds(commentIds);

        List<ChirpProductPostCommentDO> subCommentList = Collections.emptyList();
        if (Objects.nonNull(reqVo)) {
            subCommentList = baseMapper.searchSubCommentList(commentIds);
        }


        Map<Long, List<ChirpProductPostCommentDO>> parentCommentId2List = subCommentList.stream()
                .collect(Collectors.groupingBy(ChirpProductPostCommentDO::getParentCommentId));

        // 获取会员信息
        List<Long> memberIds = commentList.stream()
                .map(ChirpProductPostCommentDO::getCreator)
                .collect(Collectors.toList());
        for (ChirpProductPostCommentDO subComment : subCommentList) {
            memberIds.add(subComment.getCreator());
            Optional.ofNullable(subComment.getReplyCommentId())
                    .ifPresent(memberIds::add);
        }

        List<Long> replyMemberIdList = commentList.stream()
                .map(ChirpProductPostCommentDO::getReplyMemberId)
                .filter(Objects::nonNull)
                .toList();
        memberIds.addAll(replyMemberIdList);

        Map<Long, ChirpMemberBaseInfoBO> memberId2Info = chirpMemberService.batchGetMemberBaseInfo(memberIds);

        // 文件信息
        List<Long> allCommentIds = subCommentList.stream()
                .map(ChirpProductPostCommentDO::getCommentId)
                .collect(Collectors.toList());
        allCommentIds.addAll(commentIds);

        Map<Long, List<FileUrlVO>> commentId2FileUrl = chirpFileManagerService.batchGetFile(ProductFileManagerCodeConstant.POST_COMMENT_FILE, allCommentIds);


        // 封装列表返回对象
        List<ChirpProductPostCommentItemVO> commentItemList = new ArrayList<>();
        for (ChirpProductPostCommentDO productPostComment : commentList) {
            ChirpProductPostCommentItemVO commentItem = ChirpProductPostCommentConvert
                    .INSTANCE.toChirpProductPostCommentItemVO(productPostComment);
            commentItemList.add(commentItem);
            commentItem.setCommenterInfo(ChirpMemberConvert.INSTANCE
                    .toChirpMemberBaseInfoVO(memberId2Info.get(productPostComment.getCreator())));

            commentItem.setFileList(commentId2FileUrl.getOrDefault(productPostComment.getCommentId(), Collections.emptyList()));
            List<ChirpProductPostCommentDO> subRelyCommentListDO = parentCommentId2List
                    .getOrDefault(productPostComment.getCommentId(), Collections.emptyList());

            Optional.ofNullable(productPostComment.getReplyMemberId())
                            .ifPresent(replyMemberId -> {
                                commentItem.setBeCommenterInfo(ChirpMemberConvert.INSTANCE
                                        .toChirpMemberBaseInfoVO(memberId2Info.get(replyMemberId)));
                            });

            commentItem.setChildren(new ArrayList<>());
            for (ChirpProductPostCommentDO subRelyCommentDO : subRelyCommentListDO) {
                // 第二层回复
                ChirpProductPostCommentItemVO subRelyComment = ChirpProductPostCommentConvert
                        .INSTANCE.toChirpProductPostCommentItemVO(subRelyCommentDO);
                commentItem.getChildren().add(subRelyComment);
                subRelyComment.setFileList(commentId2FileUrl.getOrDefault(productPostComment.getCommentId(), Collections.emptyList()));
                subRelyComment.setCommenterInfo(ChirpMemberConvert.INSTANCE
                        .toChirpMemberBaseInfoVO(memberId2Info.get(subRelyCommentDO.getCreator())));
                subRelyComment.setBeCommenterInfo(ChirpMemberConvert.INSTANCE
                        .toChirpMemberBaseInfoVO(memberId2Info.get(subRelyCommentDO.getReplyMemberId())));
            }
        }

        // 封装返回对象
        ChirpProductPostCommentListRespVO respVO = new ChirpProductPostCommentListRespVO();
        respVO.setList(commentItemList);
        if (CollUtil.isNotEmpty(commentList)) {
            respVO.setNext(commentList.get(commentList.size() - 1).getCommentId());
        }
        return respVO;
    }
}
