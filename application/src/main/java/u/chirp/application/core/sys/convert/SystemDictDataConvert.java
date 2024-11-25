package u.chirp.application.core.sys.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import u.boot.start.common.pojo.R;
import u.chirp.application.core.sys.controller.app.vo.DictListGetRespVO;
import u.chirp.application.core.sys.dal.dataobject.SystemDictDataDO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Mapper
public interface SystemDictDataConvert {
    SystemDictDataConvert INSTANCE = Mappers.getMapper(SystemDictDataConvert.class);

    List<DictListGetRespVO> toDictListGetRespVO(List<SystemDictDataDO> systemDictDataList);

    DictListGetRespVO toDictListGetRespVO(SystemDictDataDO systemDictDataList);

}
