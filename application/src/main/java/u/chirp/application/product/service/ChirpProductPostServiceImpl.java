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

        Integer updateCount = baseMapper.thumbsUp(reqVo.getProductId(), reqVo.getPostId(), reqVo.getThumbsUp());
        if (updateCount == 0) {
            // 点赞失败
            return;
        }

        // 帖子的发布者点赞数 +/- 1
        ProductPostBaseInfoBO postBaseInfo = getPostBaseInfo(reqVo.getPostId());
        if (Boolean.TRUE.equals(reqVo.getThumbsUp())) {
            chirpProductMemberService.addThumbsUpCount(postBaseInfo.getProductId(), postBaseInfo.getCreator());
            // 帖子点赞要收集到表中
            chirpMemberCollectService.addCollect(CollectType.THUMBS_UP_POST, loginMemberId, reqVo.getPostId());
        } else {
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


}
