package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberLoginReqVO;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;

/**
 * @author xiaou
 * @date 2024/11/17
 */
public interface IChirpMemberService extends IService<ChirpMemberDO> {
    /**
     * 登录
     * @param reqVo
     * @return
     */
    String login(ChirpMemberLoginReqVO reqVo);
}
