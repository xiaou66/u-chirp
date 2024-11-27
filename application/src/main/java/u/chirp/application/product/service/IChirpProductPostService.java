package u.chirp.application.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import u.boot.start.common.exception.NoDataException;
import u.chirp.application.product.controller.app.vo.AppProductPostFollowReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostListGetReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostThumbsUpReqVO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;
import u.chirp.application.product.service.bo.AppProductPostListBO;
import u.chirp.application.product.service.bo.ChirpProductPostListBO;
import u.chirp.application.product.service.bo.ProductPostBaseInfoBO;

import java.util.List;

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

    /**
     * 获取单个帖子基本信息
     * @param postId
     * @return
     */
    ProductPostBaseInfoBO getPostBaseInfo(Long postId);

    /**
     * 关注
     * @param reqVo
     * @param productId
     */
    void follow(AppProductPostFollowReqVO reqVo, Long productId);

    AppProductPostListBO payloadQueryParam(AppProductPostListGetReqVO reqVo);

    List<Long> searchIdList(AppProductPostListBO appProductPostListBO) throws NoDataException;

    List<ChirpProductPostListBO> payloadResult(List<Long> ids);
}
