package u.chirp.application.product.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import u.chirp.application.mumber.enums.CollectType;
import u.chirp.application.mumber.service.IChirpMemberCollectService;
import u.chirp.application.product.controller.app.vo.AppProductPostFollowReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostListGetReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostThumbsUpReqVO;
import u.chirp.application.product.convert.ChirpProductPostConvert;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;
import u.chirp.application.product.dal.mysql.ChirpProductPostMapper;
import u.chirp.application.product.service.bo.AppProductPostListBO;
import u.chirp.application.product.service.bo.ChirpProductPostListBO;
import u.chirp.application.product.service.bo.ProductPostBaseInfoBO;

import java.util.List;

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
        try {
            verifyThumbsUp(reqVo, productId);
        } catch (Exception e) {
            return;
        }

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

    private void verifyThumbsUp(AppProductPostThumbsUpReqVO reqVo, Long productId) throws Exception {
        long loginMemberId = StpUtil.getLoginIdAsLong();
        if (Boolean.FALSE.equals(reqVo.getThumbsUp())) {
            if(!chirpMemberCollectService.hasCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId())) {
                throw exception(REPEATED_REQUESTS);
            }
        }

        if (!postExists(productId, loginMemberId)) {
            throw exception(PRODUCT_POST_EXISTENCE);
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
        if (Boolean.TRUE.equals(reqVo.getCollect())) {
            baseMapper.follow(productId, reqVo.getPostId(), reqVo.getCollect());
            chirpProductMemberService.addFollowCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子点赞要收集到表中
            chirpMemberCollectService.addCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId());
        } else {
            baseMapper.unFollow(productId, reqVo.getPostId(), reqVo.getCollect());
            chirpProductMemberService.subFollowCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子取消点赞要从收集表移除
            chirpMemberCollectService.removeCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId());
        }
    }

    @Override
    public AppProductPostListBO payloadQueryParam(AppProductPostListGetReqVO reqVo) {
        AppProductPostListBO bo = ChirpProductPostConvert.INSTANCE.toAppProductPostListBO(reqVo);
        bo.setProductId(chirpProductService.getProductIdByCode(reqVo.getProductCode()));
        return bo;
    }

    @Override
    public List<Long> searchIdList(IPage<?> page, AppProductPostListBO bo) {
        return baseMapper.searchIdList(page, bo);
    }

    @Override
    public List<ChirpProductPostListBO> payloadResult(List<Long> ids) {
        List<ChirpProductPostDO> productPostList = baseMapper.selectByIds(ids);
        List<ChirpProductPostListBO> result = ChirpProductPostConvert.INSTANCE
                .toChirpProductPostListBO(productPostList);
        return result;
    }


    private void verifyCollect(AppProductPostFollowReqVO reqVo, Long productId) throws Exception {
        long loginMemberId = StpUtil.getLoginIdAsLong();
        if (Boolean.FALSE.equals(reqVo.getCollect())) {
            if(!chirpMemberCollectService.hasCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId())) {
                throw exception(REPEATED_REQUESTS);
            }
        }

        if (!postExists(productId, loginMemberId)) {
            throw exception(PRODUCT_POST_EXISTENCE);
        }
    }

    private boolean postExists(Long productId, Long memberId) {
        return exists(Wrappers.lambdaQuery(ChirpProductPostDO.class)
                .eq(ChirpProductPostDO::getPostId, memberId)
                .eq(ChirpProductPostDO::getProductId, productId));
    }


}
