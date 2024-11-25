package u.chirp.application.core.filecenter.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileManagerDO;
import u.chirp.application.core.filecenter.dal.mysql.ChirpFileManagerMapper;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;

import java.util.List;
import java.util.Map;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Service
public class ChirpFileManagerServiceImpl extends ServiceImpl<ChirpFileManagerMapper, ChirpFileManagerDO>
        implements IChirpFileManagerService {
    /**
     * 批量获取关联文件
     *
     * @param funcCode
     * @param ids
     * @return
     */
    @Override
    public Map<Long, List<FileUrlVO>> batchGetFile(String funcCode, List<Long> ids) {
        return Map.of();
    }
}
