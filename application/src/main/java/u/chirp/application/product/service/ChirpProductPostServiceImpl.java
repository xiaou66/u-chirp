package u.chirp.application.product.service;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import u.chirp.application.product.controller.app.vo.AppProductPostActionReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
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
    public void thumbsUp(AppProductPostActionReqVO reqVo) {

    }

    @Override
    public ProductPostBaseInfoBO getPostBaseInfo(Long postId) {
        ChirpProductPostDO chirpProductPost = getOne(Wrappers.lambdaQuery(ChirpProductPostDO.class)
                .select(ChirpProductPostDO::getPostId, ChirpProductPostDO::getProductId, ChirpProductPostDO::getPostThumbsUpCount,
                        ChirpProductPostDO::getPostCollectCount, ChirpProductPostDO::getPostTop, ChirpProductPostDO::getPostType,
                        ChirpProductPostDO::getPostHandleProgress)
                .eq(ChirpProductPostDO::getPostId, postId)
                .last("limit 1"));
        return ChirpProductPostConvert.INSTANCE.convertProductPostBaseInfoBO(chirpProductPost);
    }


}
