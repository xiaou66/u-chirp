package u.chirp.application.core.filecenter.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileManagerDO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Mapper
public interface ChirpFileManagerMapper extends BaseMapper<ChirpFileManagerDO> {
    void batchInsert(@Param("funcCode") String funcCode,
                     @Param("refId") Long refId,
                     @Param("fileIds") List<Long> fileIds);
}
