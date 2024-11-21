package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.product.controller.app.vo.AppProductPostCollectReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostThumbsUpReqVO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;
import u.chirp.application.product.service.bo.ProductPostBaseInfoBO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
public interface IChirpProductPostService extends IService<ChirpProductPostDO> {
    /**
     * 保存帖子
     *
     * @param reqVo
     * @param productId
     * @return
     */
    Long savePost(AppProductPostSaveReqVO reqVo, Long productId);

    /**
     * 点赞
     *
     * @param reqVo
     * @param productId
     */
    void thumbsUp(AppProductPostThumbsUpReqVO reqVo, Long productId);

    ProductPostBaseInfoBO getPostBaseInfo(Long postId);

    void collect(AppProductPostCollectReqVO reqVo, Long productId);
}
