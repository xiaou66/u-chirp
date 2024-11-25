package u.chirp.application.core.filecenter.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import u.chirp.application.core.filecenter.local.vo.FileUrlVO;
import u.chirp.application.core.filecenter.service.bo.FileUrlBO;

/**
 * @author xiaou
 * @date 2024/11/25
 */
@Mapper
public interface FileLibraryConvert {
    FileLibraryConvert INSTANCE = Mappers.getMapper(FileLibraryConvert.class);

    FileUrlVO toFileUrlVO(FileUrlBO fileUrlBo);
}
