package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberCollectDO;
import u.chirp.application.mumber.dal.mysql.ChirpMemberCollectMapper;
import u.chirp.application.mumber.enums.CollectType;

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
                .eq(ChirpMemberCollectDO::getCollectRefId, collectMember)
                .eq(ChirpMemberCollectDO::getCollectRefId, collectRefId));
    }

    @Override
    public boolean hasCollect(CollectType collectType, Long collectMember, Long collectRefId) {
        return exists(Wrappers.lambdaQuery(ChirpMemberCollectDO.class)
                .eq(ChirpMemberCollectDO::getCollectType, collectType)
                .eq(ChirpMemberCollectDO::getCollectRefId, collectMember)
                .eq(ChirpMemberCollectDO::getCollectRefId, collectRefId));
    }

}
