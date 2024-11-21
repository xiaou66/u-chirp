package u.chirp.application.product.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import u.chirp.application.mumber.enums.CollectType;
import u.chirp.application.mumber.service.IChirpMemberCollectService;
import u.chirp.application.product.controller.app.vo.AppProductPostActionReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostCollectReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostThumbsUpReqVO;
import u.chirp.application.product.convert.ChirpProductPostConvert;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;
import u.chirp.application.product.dal.mysql.ChirpProductPostMapper;
import u.chirp.application.product.service.bo.ProductPostBaseInfoBO;

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


    @Override
    public Long savePost(AppProductPostSaveReqVO reqVo) {
        ChirpProductPostDO productPost = ChirpProductPostConvert.INSTANCE.convert(reqVo);
        saveOrUpdate(productPost);
        return productPost.getPostId();
    }

    /**
     * 点赞
     *
     * @param reqVo
     */
    @Override
    @Transactional
    public void thumbsUp(AppProductPostThumbsUpReqVO reqVo) {
        long loginMemberId = StpUtil.getLoginIdAsLong();
        try {
            verifyThumbsUp(reqVo);
        } catch (Exception e) {
            return;
        }

        // 帖子的发布者点赞数 +/- 1
        ProductPostBaseInfoBO postBaseInfo = getPostBaseInfo(reqVo.getPostId());
        if (Boolean.TRUE.equals(reqVo.getThumbsUp())) {
            baseMapper.thumbsUp(reqVo.getProductId(), reqVo.getPostId());
            chirpProductMemberService.addThumbsUpCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子点赞要收集到表中
            chirpMemberCollectService.addCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId());
        } else {
            baseMapper.unThumbsUp(reqVo.getProductId(), reqVo.getPostId());
            chirpProductMemberService.subThumbsUpCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子取消点赞要从收集表移除
            chirpMemberCollectService.removeCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId());
        }
    }

    private void verifyThumbsUp(AppProductPostThumbsUpReqVO reqVo) throws Exception {
        long loginMemberId = StpUtil.getLoginIdAsLong();
        if (Boolean.FALSE.equals(reqVo.getThumbsUp())) {
            if(!chirpMemberCollectService.hasCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId())) {
                throw new Exception();
            }
        }

        if (!postExists(reqVo.getProductId(), loginMemberId)) {
            throw new Exception();
        }
    }


    @Override
    public ProductPostBaseInfoBO getPostBaseInfo(Long postId) {
        ChirpProductPostDO chirpProductPost = getOne(Wrappers.lambdaQuery(ChirpProductPostDO.class)
                .select(ChirpProductPostDO::getPostId, ChirpProductPostDO::getProductId, ChirpProductPostDO::getPostThumbsUpCount,
                        ChirpProductPostDO::getPostCollectCount, ChirpProductPostDO::getPostTop, ChirpProductPostDO::getPostType,
                        ChirpProductPostDO::getPostHandleProgress, ChirpProductPostDO::getCreator)
                .eq(ChirpProductPostDO::getPostId, postId)
                .last("limit 1"));
        return ChirpProductPostConvert.INSTANCE.convertProductPostBaseInfoBO(chirpProductPost);
    }

    @Override
    @Transactional
    public void collect(AppProductPostCollectReqVO reqVo) {
        long loginMemberId = StpUtil.getLoginIdAsLong();
        try {
            verifyCollect(reqVo);
        } catch (Exception e) {
            return;
        }

        // 帖子的发布者点赞数 +/- 1
        ProductPostBaseInfoBO postBaseInfo = getPostBaseInfo(reqVo.getPostId());
        if (Boolean.TRUE.equals(reqVo.getCollect())) {
            baseMapper.collect(reqVo.getProductId(), reqVo.getPostId(), reqVo.getCollect());
            chirpProductMemberService.addCollectCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子点赞要收集到表中
            chirpMemberCollectService.addCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId());
        } else {
            baseMapper.unCollect(reqVo.getProductId(), reqVo.getPostId(), reqVo.getCollect());
            chirpProductMemberService.subCollectCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子取消点赞要从收集表移除
            chirpMemberCollectService.removeCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId());
        }
    }



    private void verifyCollect(AppProductPostCollectReqVO reqVo) throws Exception {
        long loginMemberId = StpUtil.getLoginIdAsLong();
        if (Boolean.FALSE.equals(reqVo.getCollect())) {
            if(!chirpMemberCollectService.hasCollect(CollectType.COLLECT_POST, loginMemberId, reqVo.getPostId())) {
                throw new Exception();
            }
        }

        if (!postExists(reqVo.getProductId(), loginMemberId)) {
            throw new Exception();
        }
    }

    private boolean postExists(Long productId, Long memberId) {
        return exists(Wrappers.lambdaQuery(ChirpProductPostDO.class)
                .eq(ChirpProductPostDO::getPostId, memberId)
                .eq(ChirpProductPostDO::getProductId, productId));
    }


}
