package u.chirp.application.core.filecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileManagerDO;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;

import java.util.List;
import java.util.Map;

/**
 * @author xiaou
 * @date 2024/11/21
 */
public interface IChirpFileManagerService extends IService<ChirpFileManagerDO> {
    /**
     * 批量获取关联文件
     * @param funcCode
     * @param ids
     * @return
     */
    Map<Long, List<FileUrlVO>> batchGetFile(String funcCode, List<Long> ids);
}
