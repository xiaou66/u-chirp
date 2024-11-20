package u.chirp.application.mumber.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberAccountDO;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Mapper
public interface ChirpMemberAccountMapper extends BaseMapper<ChirpMemberAccountDO> {
}
