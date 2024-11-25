package u.chirp.application.core.filecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileLibraryDO;
import u.chirp.application.core.filecenter.service.bo.FileUrlBO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/21
 */
public interface IChirpFileLibraryService extends IService<ChirpFileLibraryDO> {
    List<FileUrlBO> generateUrl(List<Long> fileIds);
}
