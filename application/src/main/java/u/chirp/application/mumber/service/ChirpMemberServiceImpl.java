package u.chirp.application.mumber.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;
import u.chirp.application.mumber.MemberErrorCodeConstants;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberAppLoginReqVO;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberLoginReqVO;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;
import u.chirp.application.mumber.dal.mysql.ChirpMemberMapper;
import u.chirp.application.mumber.enums.MemberStatus;

import java.util.Objects;

import static u.boot.start.common.exception.util.ServiceExceptionUtil.exception;


/**
 * @author xiaou
 * @date 2024/11/17
 */
@Service
public class ChirpMemberServiceImpl extends ServiceImpl<ChirpMemberMapper, ChirpMemberDO>
        implements IChirpMemberService{



    @Override
    public String login(ChirpMemberLoginReqVO reqVo) {
        String hashPassword = Hashing.sha256()
                .hashString(reqVo.getPassword(), Charsets.UTF_8)
                .toString();

        ChirpMemberDO chirpMember = getOne(Wrappers.lambdaQuery(ChirpMemberDO.class)
                .eq(ChirpMemberDO::getEmail, reqVo.getEmail())
                .eq(ChirpMemberDO::getMemberPassword, hashPassword)
                .eq(ChirpMemberDO::getMemberStatus, MemberStatus.NORMAL)
                .last("limit 1"));

        if (Objects.isNull(chirpMember)) {
            throw exception(MemberErrorCodeConstants.LOGIN_ERROR);
        }

        StpUtil.login(chirpMember.getMemberId(), "app");

        return StpUtil.getTokenValue();
    }

    @Override
    public String appLogin(ChirpMemberAppLoginReqVO reqVo) {
        return "";
    }
}
