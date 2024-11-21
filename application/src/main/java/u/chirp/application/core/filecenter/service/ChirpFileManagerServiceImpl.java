package u.chirp.application.core.filecenter.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileManagerDO;
import u.chirp.application.core.filecenter.dal.mysql.ChirpFileManagerMapper;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Service
public class ChirpFileManagerServiceImpl extends ServiceImpl<ChirpFileManagerMapper, ChirpFileManagerDO>
        implements IChirpFileManagerService {
}
