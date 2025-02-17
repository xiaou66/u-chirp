package u.chirp.application.mumber.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.xiaou66.sdk.BaseClientException;
import io.github.xiaou66.sdk.client.ClientExecutor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import u.boot.start.common.exception.enums.GlobalErrorCodeConstants;
import u.boot.start.common.exception.util.ServiceExceptionUtil;
import u.boot.start.utools.UtoolsBaseResponse;
import u.boot.start.utools.UtoolsClient;
import u.boot.start.utools.info.UtoolsUserInfoRequest;
import u.boot.start.utools.info.UtoolsUserInfoResponse;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberThirdPartyLoginReqVO;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberUtoolsLoginReqVO;
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
@Slf4j
public class ChirpMemberAccountServiceImpl extends ServiceImpl<ChirpMemberAccountMapper, ChirpMemberAccountDO>
        implements IChirpMemberAccountService {

    @Resource
    private IChirpMemberService chirpMemberService;

    @Resource
    private IChirpProductMemberService chirpProductMemberService;


    @Override
    public MemberLoginSuccessInfoBO thirdPartyLogin(ChirpMemberThirdPartyLoginReqVO reqVo, ChirpSocialAccountDO chirpSocialAccount) {
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

    @Override
    public MemberLoginSuccessInfoBO utoolsLogin(ChirpMemberUtoolsLoginReqVO reqVo,
                                                ChirpSocialAccountDO chirpSocialAccount) {
        String accessToken = reqVo.getAccessToken();
        UtoolsClient utoolsClient = UtoolsClient.getUtoolsClient(chirpSocialAccount.getSocialClientId(), chirpSocialAccount.getSocialClientSecret());
        UtoolsUserInfoRequest request = new UtoolsUserInfoRequest();
        request.setPluginId(chirpSocialAccount.getSocialClientId());
        request.setAccessToken(accessToken);

        UtoolsBaseResponse<UtoolsUserInfoResponse> response = null;
        try {
            response = ClientExecutor.execute(utoolsClient, request);
        } catch (BaseClientException e) {
            log.error("utools 登录失败--utoolsLogin--clientId: {}", reqVo.getClientId(),  e);
            throw ServiceExceptionUtil.exception(GlobalErrorCodeConstants.ERROR_CONFIGURATION);
        }

        ChirpMemberAccountDO memberAccount = Optional.ofNullable(getOne(Wrappers.lambdaQuery(ChirpMemberAccountDO.class)
                        .eq(ChirpMemberAccountDO::getSocialId, chirpSocialAccount.getSocialId())
                        .eq(ChirpMemberAccountDO::getOpenId, response.getResource().getOpenId())
                        .last("limit 1")))
                .orElse(new ChirpMemberAccountDO());

        ChirpMemberAccountConvert.INSTANCE.convert(reqVo, response.getResource(), memberAccount);
        if (Objects.isNull(memberAccount.getAccountId())) {
            GenerateMemberBO generateMemberBO = new GenerateMemberBO();
            generateMemberBO.setMemberAvatar(memberAccount.getAccountAvatar());
            generateMemberBO.setMemberNickname(memberAccount.getAccountName());
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
