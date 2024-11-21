package u.chirp.application.mumber.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberAppLoginReqVO;
import u.chirp.application.mumber.convert.ChirpMemberAccountConvert;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberAccountDO;
import u.chirp.application.mumber.dal.dataobject.ChirpSocialAccountDO;
import u.chirp.application.mumber.dal.mysql.ChirpMemberAccountMapper;
import u.chirp.application.mumber.service.bo.GenerateMemberBO;
import u.chirp.application.mumber.service.bo.MemberLoginSuccessInfoBO;
import u.chirp.application.product.service.IChirpProductMemberService;

import java.util.Objects;
import java.util.Optional;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Service
public class ChirpMemberAccountServiceImpl extends ServiceImpl<ChirpMemberAccountMapper, ChirpMemberAccountDO>
        implements IChirpMemberAccountService {

    @Resource
    private IChirpMemberService chirpMemberService;

    @Resource
    private IChirpProductMemberService chirpProductMemberService;


    @Override
    public MemberLoginSuccessInfoBO thirdPartyLogin(ChirpMemberAppLoginReqVO reqVo, ChirpSocialAccountDO chirpSocialAccount) {
        ChirpMemberAccountDO memberAccount = Optional.ofNullable(getOne(Wrappers.lambdaQuery(ChirpMemberAccountDO.class)
                .eq(ChirpMemberAccountDO::getSocialId, chirpSocialAccount.getSocialId())
                .eq(ChirpMemberAccountDO::getOpenId, reqVo.getOpenId())
                .last("limit 1")))
                .orElse(new ChirpMemberAccountDO());

        boolean isNew = Objects.isNull(memberAccount.getAccountId());

        ChirpMemberAccountConvert.INSTANCE.convert(reqVo, memberAccount);

        if (isNew) {
            GenerateMemberBO generateMemberBO = new GenerateMemberBO();
            generateMemberBO.setMemberAvatar(reqVo.getAvatar());
            generateMemberBO.setMemberNickname(reqVo.getUsername());
            Long memberId = chirpMemberService.generateUser(generateMemberBO);
            memberAccount.setBindMemberId(memberId);

            // 绑定产品
            chirpProductMemberService.createMemberAccount(memberId, chirpSocialAccount.getSocialProductId());
        }
        saveOrUpdate(memberAccount);
        StpUtil.login(memberAccount.getBindMemberId(), "app");

        return new MemberLoginSuccessInfoBO()
                .setMemberId(memberAccount.getBindMemberId())
                .setToken(StpUtil.getTokenValue());
    }

}
