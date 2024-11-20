package u.chirp.application.mumber.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberCollectDO;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Mapper
public interface ChirpMemberCollectMapper extends BaseMapper<ChirpMemberCollectDO> {

    void insertIfNotExists(@Param("collectType") Integer collectType,
                           @Param("collectMember") Long collectMember,
                           @Param("collectRefId") Long collectRefId);
}
