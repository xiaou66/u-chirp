package u.chirp.application.system.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import u.chirp.application.system.dal.dataobject.SysUserDO;

/**
 * @author xiaou
 * @date 2024/11/7
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserDO> {
}
