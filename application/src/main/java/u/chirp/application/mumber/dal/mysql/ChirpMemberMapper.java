package u.chirp.application.mumber.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@Mapper
public interface ChirpMemberMapper extends BaseMapper<ChirpMemberDO> {
}
