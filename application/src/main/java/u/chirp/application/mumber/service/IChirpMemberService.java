package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberLoginReqVO;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;
import u.chirp.application.mumber.service.bo.ChirpMemberBaseInfoBO;
import u.chirp.application.mumber.service.bo.GenerateMemberBO;
import u.chirp.application.mumber.service.bo.MemberLoginSuccessInfoBO;

import java.util.Collection;
import java.util.Map;

/**
 * @author xiaou
 * @date 2024/11/17
 */
public interface IChirpMemberService extends IService<ChirpMemberDO> {
    /**
     * 登录
     *
     * @param reqVo
     * @return
     */
    MemberLoginSuccessInfoBO login(ChirpMemberLoginReqVO reqVo);

    Long generateUser(GenerateMemberBO generateMemberBO);

    /**
     * 登录之后
     * @param memberId
     */
    void loginAfter(Long memberId);

    /**
     * 批量获取会员基本信息
     * @param memberIds 会员
     * @return
     */
    Map<Long, ChirpMemberBaseInfoBO> batchGetMemberBaseInfo(Collection<Long> memberIds);

    /**
     * 获取会员基本信息
     * @param memberId 会员
     * @return 会员基本信息
     */
    ChirpMemberBaseInfoBO getMemberBaseInfo(Long memberId);

}
