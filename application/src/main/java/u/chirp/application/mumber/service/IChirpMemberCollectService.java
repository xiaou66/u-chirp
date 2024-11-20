package u.chirp.application.mumber.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberCollectDO;
import u.chirp.application.mumber.enums.CollectType;

/**
 * @author xiaou
 * @date 2024/11/20
 */
public interface IChirpMemberCollectService extends IService<ChirpMemberCollectDO> {

    /**
     * 添加收集资源
     * @param collectType 收藏类型
     * @param collectMember 收藏人
     * @param CollectRefId 资源 id
     */
    void addCollect(CollectType collectType, Long collectMember, Long CollectRefId);

    /**
     * 移除收集资源
     * @param collectType 收藏类型
     * @param collectMember 收藏人
     * @param collectRefId 资源 id
     */
    void removeCollect(CollectType collectType, Long collectMember, Long collectRefId);

    /**
     * 是否收藏
     * @param collectType 收藏类型
     * @param collectMember 收藏人
     * @param collectRefId 收藏人
     * @return
     */
    boolean hasCollect(CollectType collectType, Long collectMember, Long collectRefId);
}
