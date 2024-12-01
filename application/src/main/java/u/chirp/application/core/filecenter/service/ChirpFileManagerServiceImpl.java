package u.chirp.application.core.filecenter.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import u.chirp.application.core.filecenter.convert.FileLibraryConvert;
import u.chirp.application.core.filecenter.dal.dataobejct.ChirpFileManagerDO;
import u.chirp.application.core.filecenter.dal.mysql.ChirpFileManagerMapper;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;
import u.chirp.application.core.filecenter.service.bo.FileUrlBO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Service
public class ChirpFileManagerServiceImpl extends ServiceImpl<ChirpFileManagerMapper, ChirpFileManagerDO>
        implements IChirpFileManagerService {

    @Resource
    @Lazy
    private IChirpFileLibraryService chirpFileLibraryService;

    /**
     * 批量获取关联文件
     *
     * @param funcCode
     * @param ids
     * @return
     */
    @Override
    public Map<Long, List<FileUrlVO>> batchGetFile(String funcCode, List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Map.of();
        }

        ids = ids.stream().distinct().toList();
        List<ChirpFileManagerDO> chirpList = baseMapper.selectList(Wrappers.lambdaQuery(ChirpFileManagerDO.class)
                .select(ChirpFileManagerDO::getFileId, ChirpFileManagerDO::getRefId)
                .in(ChirpFileManagerDO::getRefId, ids)
                .eq(ChirpFileManagerDO::getFuncCode, funcCode));

        // 文件对应关联 id
        Map<Long, Long> fileId2RefId = chirpList.stream()
                .collect(Collectors.toMap(ChirpFileManagerDO::getFileId, ChirpFileManagerDO::getRefId));

        List<Long> fileIds = chirpList.stream()
                .map(ChirpFileManagerDO::getFileId)
                .toList();

        // 返回文件预览列表
        List<FileUrlBO> fileUrlBoList = chirpFileLibraryService.generateUrl(fileIds);

        Map<Long, List<FileUrlVO>> result = new HashMap<>();
        for (FileUrlBO fileUrlBo : fileUrlBoList) {
            FileUrlVO fileUrlVo = FileLibraryConvert.INSTANCE.toFileUrlVO(fileUrlBo);
            Long refId = fileId2RefId.get(fileUrlBo.getFileId());
            result.computeIfPresent(refId,  (key, value) -> {
                value.add(fileUrlVo);
                return value;
            });
            result.putIfAbsent(refId, CollUtil.newArrayList(fileUrlVo));
        }
        return result;
    }

    @Override
    public List<FileUrlVO> getFileList(String funcCode, Long refId) {
        return batchGetFile(funcCode, Collections.singletonList(refId))
                .getOrDefault(refId, Collections.emptyList());
    }

    @Override
    @Transactional
    public void batchSaveRefFileIds(String funcCode, Long refId, List<Long> fileIds) {
        List<Long> fileIdsInDb = baseMapper.selectList(Wrappers.lambdaQuery(ChirpFileManagerDO.class)
                        .select(ChirpFileManagerDO::getFileId)
                        .eq(ChirpFileManagerDO::getFuncCode, funcCode)
                        .eq(ChirpFileManagerDO::getRefId, refId))
                .stream()
                .map(ChirpFileManagerDO::getFileId)
                .toList();

        if (CollUtil.isNotEmpty(fileIds)) {
            CollUtil.split(fileIds, 30)
                    .forEach(fileIdList -> baseMapper.batchInsert(funcCode, refId, fileIdList));
        }

        // 当前资源关联过文件
        if (CollUtil.isNotEmpty(fileIdsInDb)) {

            // 删除的文件 id
            List<Long> deleteFileIds = fileIdsInDb.stream()
                    .filter(fileId -> !fileIds.contains(fileId))
                    .toList();

            if (CollUtil.isNotEmpty(deleteFileIds)) {
                CollUtil.split(fileIds, 30)
                        .forEach(fileIdList -> baseMapper.delete(Wrappers.lambdaQuery(ChirpFileManagerDO.class)
                                .in(ChirpFileManagerDO::getFileId, fileIdList)
                                .eq(ChirpFileManagerDO::getRefId, refId)
                                .eq(ChirpFileManagerDO::getFuncCode, funcCode)));
            }
        }
    }
}
