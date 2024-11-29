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
     * @param funcCode 关联功能
     * @param refIds 关联资源 id
     * @return
     */
    Map<Long, List<FileUrlVO>> batchGetFile(String funcCode, List<Long> refIds);

    /**
     * 批量保存文件的关联关系 <br/>
     * 内置本次保存没有包含在 fileIds 参数将会删除即覆盖保存
     * @param funcCode 关联功能 code
     * @param refId 关联资源 id
     * @param fileIds 文件 ids
     */
    void batchSaveRefFileIds(String funcCode, Long refId, List<Long> fileIds);
}
