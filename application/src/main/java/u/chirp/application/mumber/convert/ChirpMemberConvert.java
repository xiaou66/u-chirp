package u.chirp.application.mumber.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;
import u.chirp.application.mumber.service.bo.generateMemberBO;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Mapper
public interface ChirpMemberConvert {
    ChirpMemberConvert INSTANCE = Mappers.getMapper(ChirpMemberConvert.class);

    ChirpMemberDO convert(generateMemberBO generateMemberBO);
}
