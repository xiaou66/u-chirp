package u.chirp.application.core.filecenter.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import u.chirp.application.core.filecenter.FileStorageStrategyHolder;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileLibraryDO;
import u.chirp.application.core.filecenter.dal.mysql.ChirpFileLibraryMapper;
import u.chirp.application.core.filecenter.service.bo.FileUrlBO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Service
public class ChirpFileLibraryServiceImpl extends ServiceImpl<ChirpFileLibraryMapper, ChirpFileLibraryDO>
        implements IChirpFileLibraryService {

    @Resource
    @Lazy
    private FileStorageStrategyHolder fileStorageStrategyHolder;

    @Override
    public List<FileUrlBO> generateUrl(List<Long> fileIds) {
        if (CollUtil.isEmpty(fileIds)) {
            return List.of();
        }

        List<ChirpFileLibraryDO> chirpFileLibraryList = baseMapper.selectList(Wrappers.lambdaQuery(ChirpFileLibraryDO.class)
                        .select(ChirpFileLibraryDO::getFileId, ChirpFileLibraryDO::getUploadSource)
                .in(ChirpFileLibraryDO::getFileId, fileIds));

        return chirpFileLibraryList.stream()
                .map(item -> fileStorageStrategyHolder.getHandler(item.getUploadSource())
                                .generateUrl(item.getFileId()))
                .toList();
    }
}
