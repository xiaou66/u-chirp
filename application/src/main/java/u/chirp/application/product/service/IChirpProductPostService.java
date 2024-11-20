package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
public interface IChirpProductPostService extends IService<ChirpProductPostDO> {
    /**
     * 保存帖子
     * @param reqVo
     * @return
     */
    Long savePost(AppProductPostSaveReqVO reqVo);
}
