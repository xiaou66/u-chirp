package u.chirp.application.product.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import u.boot.start.common.exception.NoDataException;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;
import u.chirp.application.core.filecenter.service.IChirpFileManagerService;
import u.chirp.application.mumber.enums.CollectType;
import u.chirp.application.mumber.service.IChirpMemberCollectService;
import u.chirp.application.mumber.service.IChirpMemberService;
import u.chirp.application.mumber.service.bo.ChirpMemberBaseInfoBO;
import u.chirp.application.product.constant.ProductFileManagerCodeConstant;
import u.chirp.application.product.controller.app.vo.AppProductPostFollowReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostListGetReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostThumbsUpReqVO;
import u.chirp.application.product.convert.ChirpProductPostConvert;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;
import u.chirp.application.product.dal.mysql.ChirpProductPostMapper;
import u.chirp.application.product.enums.AppProductPostListTab;
import u.chirp.application.product.service.bo.AppProductPostListBO;
import u.chirp.application.product.service.bo.ChirpProductPostListBO;
import u.chirp.application.product.service.bo.ProductPostBaseInfoBO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static u.boot.start.common.exception.enums.GlobalErrorCodeConstants.REPEATED_REQUESTS;
import static u.boot.start.common.exception.util.ServiceExceptionUtil.exception;
import static u.chirp.application.product.ProductErrorCodeConstants.PRODUCT_POST_EXISTENCE;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Service
public class ChirpProductPostServiceImpl extends ServiceImpl<ChirpProductPostMapper, ChirpProductPostDO>
        implements IChirpProductPostService {

    @Resource
    private IChirpProductMemberService chirpProductMemberService;

    @Resource
    private IChirpMemberCollectService chirpMemberCollectService;

    @Resource
    private IChirpProductService chirpProductService;

    @Resource
    private IChirpFileManagerService chirpFileManagerService;

    @Resource
    private IChirpMemberService chirpMemberService;

    @Override
    public Long savePost(AppProductPostSaveReqVO reqVo, Long productId) {
        ChirpProductPostDO productPost = ChirpProductPostConvert.INSTANCE.convert(reqVo);
        productPost.setProductId(productId);
        saveOrUpdate(productPost);
        return productPost.getPostId();
    }

    /**
     * 点赞
     *
     * @param reqVo
     * @param productId
     */
    @Override
    @Transactional
    public void thumbsUp(AppProductPostThumbsUpReqVO reqVo, Long productId) {
        long loginMemberId = StpUtil.getLoginIdAsLong();
        verifyThumbsUp(reqVo, productId);

        // 帖子的发布者点赞数 +/- 1
        ProductPostBaseInfoBO postBaseInfo = getPostBaseInfo(reqVo.getPostId());
        if (Boolean.TRUE.equals(reqVo.getThumbsUp())) {
            baseMapper.thumbsUp(productId, reqVo.getPostId());
            chirpProductMemberService.addThumbsUpCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子点赞要收集到表中
            chirpMemberCollectService.addCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId());
        } else {
            baseMapper.unThumbsUp(productId, reqVo.getPostId());
            chirpProductMemberService.subThumbsUpCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子取消点赞要从收集表移除
            chirpMemberCollectService.removeCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId());
        }
    }

    private void verifyThumbsUp(AppProductPostThumbsUpReqVO reqVo, Long productId)  {
        if (!postExists(productId, reqVo.getPostId())) {
            throw exception(PRODUCT_POST_EXISTENCE);
        }

        long loginMemberId = StpUtil.getLoginIdAsLong();
        if (Boolean.FALSE.equals(reqVo.getThumbsUp())) {
            if(!chirpMemberCollectService.hasCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId())) {
                throw exception(REPEATED_REQUESTS);
            }
        } else if (Boolean.TRUE.equals(reqVo.getThumbsUp())) {
            if(chirpMemberCollectService.hasCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId())) {
                throw exception(REPEATED_REQUESTS);
            }
        }
    }


    @Override
    public ProductPostBaseInfoBO getPostBaseInfo(Long postId) {
        ChirpProductPostDO chirpProductPost = getOne(Wrappers.lambdaQuery(ChirpProductPostDO.class)
                .select(ChirpProductPostDO::getPostId, ChirpProductPostDO::getProductId, ChirpProductPostDO::getPostThumbsUpCount,
                        ChirpProductPostDO::getPostFollowCount, ChirpProductPostDO::getPostTop, ChirpProductPostDO::getPostType,
                        ChirpProductPostDO::getPostHandleProgress, ChirpProductPostDO::getCreator)
                .eq(ChirpProductPostDO::getPostId, postId)
                .last("limit 1"));
        return ChirpProductPostConvert.INSTANCE.convertProductPostBaseInfoBO(chirpProductPost);
    }

    @Override
    @Transactional
    public void follow(AppProductPostFollowReqVO reqVo, Long productId) {
        long loginMemberId = StpUtil.getLoginIdAsLong();
        try {
            verifyCollect(reqVo, productId);
        } catch (Exception e) {
            return;
        }

        // 帖子的发布者点赞数 +/- 1
        ProductPostBaseInfoBO postBaseInfo = getPostBaseInfo(reqVo.getPostId());
        if (Boolean.TRUE.equals(reqVo.getFollow())) {
            baseMapper.follow(productId, reqVo.getPostId(), reqVo.getFollow());
            chirpProductMemberService.addFollowCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子点赞要收集到表中
            chirpMemberCollectService.addCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId());
        } else {
            baseMapper.unFollow(productId, reqVo.getPostId(), reqVo.getFollow());
            chirpProductMemberService.subFollowCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子取消点赞要从收集表移除
            chirpMemberCollectService.removeCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId());
        }
    }

    @Override
    public AppProductPostListBO payloadQueryParam(AppProductPostListGetReqVO reqVo) {
        AppProductPostListBO bo = ChirpProductPostConvert.INSTANCE.toAppProductPostListBO(reqVo);
        bo.setProductId(chirpProductService.getProductIdByCode(reqVo.getProductCode()));
        bo.setMemberId(StpUtil.getLoginIdAsLong());
        if (AppProductPostListTab.FOLLOW.equals(reqVo.getTab())) {
            bo.setJoinCollectTable(true);
        }
        return bo;
    }

    @Override
    public List<Long> searchIdList(AppProductPostListBO bo) throws NoDataException {
        List<Long> ids = baseMapper.searchIdList(bo);
        if (CollUtil.isEmpty(ids)) {
            throw new NoDataException();
        }
        return baseMapper.searchIdList(bo);
    }

    @Override
    public List<ChirpProductPostListBO> payloadResult(List<Long> ids) {
        List<ChirpProductPostDO> productPostList = baseMapper.selectByIds(ids);

        Map<Long, List<FileUrlVO>> refId2FileUrlVO = chirpFileManagerService.batchGetFile(ProductFileManagerCodeConstant.POST_IMAGE, ids);
        List<Long> memberIds = productPostList.stream()
                .map(ChirpProductPostDO::getCreator)
                .distinct()
                .toList();
        Map<Long, ChirpMemberBaseInfoBO> memberId2Info = chirpMemberService.batchGetMemberBaseInfo(memberIds);
        List<ChirpProductPostListBO> result = new ArrayList<>();
        for (ChirpProductPostDO productPost : productPostList) {
            ChirpProductPostListBO data = ChirpProductPostConvert.INSTANCE
                    .toChirpProductPostListBO(productPost);
            result.add(data);
            data.setFileList(refId2FileUrlVO.getOrDefault(productPost.getPostId(), Collections.emptyList()));
            data.setMemberInfo(memberId2Info.get(productPost.getCreator()));
        }
        return result;
    }


    private void verifyCollect(AppProductPostFollowReqVO reqVo, Long productId) throws Exception {
        if (!postExists(productId, reqVo.getPostId())) {
            throw exception(PRODUCT_POST_EXISTENCE);
        }

        long loginMemberId = StpUtil.getLoginIdAsLong();
        if (Boolean.FALSE.equals(reqVo.getFollow())) {
            if(!chirpMemberCollectService.hasCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId())) {
                throw exception(REPEATED_REQUESTS);
            }
        } else if (Boolean.TRUE.equals(reqVo.getFollow())) {
            if(chirpMemberCollectService.hasCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId())) {
                throw exception(REPEATED_REQUESTS);
            }
        }


    }

    private boolean postExists(Long productId, Long postId) {
        return exists(Wrappers.lambdaQuery(ChirpProductPostDO.class)
                .eq(ChirpProductPostDO::getPostId, postId)
                .eq(ChirpProductPostDO::getProductId, productId));
    }


}
