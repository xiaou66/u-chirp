package u.chirp.application.core.sys.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import u.chirp.application.core.sys.dal.dataobject.SystemDictTypeDO;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Mapper
public interface SystemDictTypeMapper extends BaseMapper<SystemDictTypeDO> {
}
