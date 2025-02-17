package u.chirp.application.mumber.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;
import u.chirp.application.mumber.MemberErrorCodeConstants;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberLoginReqVO;
import u.chirp.application.mumber.convert.ChirpMemberConvert;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;
import u.chirp.application.mumber.dal.mysql.ChirpMemberMapper;
import u.chirp.application.mumber.enums.MemberStatus;
import u.chirp.application.mumber.service.bo.ChirpMemberBaseInfoBO;
import u.chirp.application.mumber.service.bo.GenerateMemberBO;
import u.chirp.application.mumber.service.bo.MemberLoginSuccessInfoBO;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static u.boot.start.common.exception.util.ServiceExceptionUtil.exception;


/**
 * @author xiaou
 * @date 2024/11/17
 */
@Service
public class ChirpMemberServiceImpl extends ServiceImpl<ChirpMemberMapper, ChirpMemberDO>
        implements IChirpMemberService {


    @Override
    public MemberLoginSuccessInfoBO login(ChirpMemberLoginReqVO reqVo) {
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

        MemberLoginSuccessInfoBO successInfo = new MemberLoginSuccessInfoBO();
        successInfo.setMemberId(chirpMember.getMemberId());
        successInfo.setToken(StpUtil.getTokenValue());
        return successInfo;
    }

    @Override
    public void loginAfter(Long memberId) {

    }

    @Override
    public Map<Long, ChirpMemberBaseInfoBO> batchGetMemberBaseInfo(Collection<Long> memberIds) {
        if (CollUtil.isEmpty(memberIds)) {
            return Map.of();
        }

        return CollUtil.split(CollUtil.newHashSet(memberIds), 30).stream()
                .map(memeberIdList -> baseMapper.selectList(Wrappers.lambdaQuery(ChirpMemberDO.class)
                        .select(ChirpMemberDO::getMemberId, ChirpMemberDO::getMemberNickname, ChirpMemberDO::getMemberAvatar)
                        .in(ChirpMemberDO::getMemberId, memeberIdList))
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(ChirpMemberDO::getMemberId, ChirpMemberConvert.INSTANCE::toChirpMemberBaseInfoBO));
    }

    @Override
    public ChirpMemberBaseInfoBO getMemberBaseInfo(Long memberId) {
        ChirpMemberDO chirpMember = getOne(Wrappers.lambdaQuery(ChirpMemberDO.class)
                .select(ChirpMemberDO::getMemberId, ChirpMemberDO::getMemberNickname, ChirpMemberDO::getMemberAvatar)
                .eq(ChirpMemberDO::getMemberId, memberId)
                .last("limit 1"));
        return ChirpMemberConvert.INSTANCE.toChirpMemberBaseInfoBO(chirpMember);
    }

    @Override
    public Long generateUser(GenerateMemberBO generateMemberBO) {
        ChirpMemberDO chirpMemberDO = ChirpMemberConvert.INSTANCE.convert(generateMemberBO);
        baseMapper.insert(chirpMemberDO);
        return chirpMemberDO.getMemberId();
    }
}
