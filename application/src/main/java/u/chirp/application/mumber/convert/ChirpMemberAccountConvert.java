package u.chirp.application.mumber.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberAppLoginReqVO;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberAccountDO;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Mapper
public interface ChirpMemberAccountConvert {
    ChirpMemberAccountConvert INSTANCE = Mappers.getMapper(ChirpMemberAccountConvert.class);


    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "mobile", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    // TODO json
    @Mapping(target = "accountRawData", ignore = true)
    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "accountName", source = "username")
    @Mapping(target = "accountAvatar", source = "avatar")
    @Mapping(target = "socialId", source = "clientId")
    @Mapping(target = "bindMemberId", ignore = true)
    void convert(ChirpMemberAppLoginReqVO reqVo, @MappingTarget ChirpMemberAccountDO memberAccount);
}
