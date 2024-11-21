package u.chirp.application.core.filecenter.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileLibraryDO;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Mapper
public interface ChirpFileLibraryMapper extends BaseMapper<ChirpFileLibraryDO> {
}
