package u.chirp.application.mumber.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberCollectDO;
import u.chirp.application.mumber.dal.mysql.ChirpMemberCollectMapper;
import u.chirp.application.mumber.enums.CollectType;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Service
public class ChirpMemberCollectServiceImpl extends ServiceImpl<ChirpMemberCollectMapper, ChirpMemberCollectDO>
        implements IChirpMemberCollectService {


    @Override
    public void addCollect(CollectType collectType, Long collectMember, Long collectRefId) {
        baseMapper.insertIfNotExists(collectType.getValue(), collectMember, collectRefId);
    }

    @Override
    public void removeCollect(CollectType collectType, Long collectMember, Long collectRefId) {
        baseMapper.delete(Wrappers.lambdaQuery(ChirpMemberCollectDO.class)
                .eq(ChirpMemberCollectDO::getCollectType, collectType)
                .eq(ChirpMemberCollectDO::getCollectMemberId, collectMember)
                .eq(ChirpMemberCollectDO::getCollectRefId, collectRefId));
    }

    @Override
    public boolean hasCollect(CollectType collectType, Long collectMember, Long collectRefId) {
        return Objects.nonNull(getOne(Wrappers.lambdaQuery(ChirpMemberCollectDO.class)
                .select(ChirpMemberCollectDO::getCollectId)
                .eq(ChirpMemberCollectDO::getCollectType, collectType)
                .eq(ChirpMemberCollectDO::getCollectMemberId, collectMember)
                .eq(ChirpMemberCollectDO::getCollectRefId, collectRefId)
                .last("limit 1")));
    }

    @Override
    public List<Long> extractExistCollect(CollectType collectType, long collectMember, List<Long> refIds) {
        if (CollUtil.isEmpty(refIds)) {
            return List.of();
        }

        return CollUtil.split(refIds, 50).stream()
                .map(item -> baseMapper.selectList(Wrappers.lambdaQuery(ChirpMemberCollectDO.class)
                            .eq(ChirpMemberCollectDO::getCollectMemberId, collectMember)
                            .in(ChirpMemberCollectDO::getCollectRefId, refIds))
                            .stream()
                            .map(ChirpMemberCollectDO::getCollectRefId)
                            .toList())
                .flatMap(Collection::stream)
                .toList();
    }

}
