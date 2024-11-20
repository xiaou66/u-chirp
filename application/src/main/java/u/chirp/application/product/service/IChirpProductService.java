package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.product.controller.app.vo.ChirpProductCreateReqVO;
import u.chirp.application.product.dal.dataobject.ChirpProductDO;

/**
 * @author xiaou
 * @date 2024/11/17
 */
public interface IChirpProductService extends IService<ChirpProductDO> {
    /**
     * 创建产品
     * @param reqVo
     * @return
     */
    Long create(ChirpProductCreateReqVO reqVo);
}
