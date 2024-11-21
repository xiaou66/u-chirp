package u.chirp.application.core.filecenter.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileLibraryDO;
import u.chirp.application.core.filecenter.dal.mysql.ChirpFileLibraryMapper;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Service
public class ChirpFileLibraryServiceImpl extends ServiceImpl<ChirpFileLibraryMapper, ChirpFileLibraryDO>
        implements IChirpFileLibraryService{
}
