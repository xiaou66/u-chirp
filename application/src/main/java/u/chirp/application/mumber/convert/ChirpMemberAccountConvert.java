package u.chirp.application.mumber.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import u.boot.start.common.exception.util.json.JsonUtils;
import u.boot.start.utools.info.UtoolsUserInfoResponse;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberThirdPartyLoginReqVO;
import u.chirp.application.mumber.controller.app.vo.ChirpMemberUtoolsLoginReqVO;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberAccountDO;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Mapper(imports = {JsonUtils.class})
public interface ChirpMemberAccountConvert {
    ChirpMemberAccountConvert INSTANCE = Mappers.getMapper(ChirpMemberAccountConvert.class);


    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "mobile", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "accountName", source = "username")
    @Mapping(target = "accountAvatar", source = "avatar")
    @Mapping(target = "socialId", source = "clientId")
    @Mapping(target = "bindMemberId", ignore = true)
    @Mapping(target = "accountRawData", expression = "java(JsonUtils.toJsonString(reqVo))")
    void convert(ChirpMemberThirdPartyLoginReqVO reqVo, @MappingTarget ChirpMemberAccountDO memberAccount);


    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "mobile", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "bindMemberId", ignore = true)
    @Mapping(target = "accountStatus", ignore = true)
    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "accountAvatar", source = "resource.avatar")
    @Mapping(target = "socialId", source = "reqVo.clientId")
    @Mapping(target = "accountName", source = "resource.nickname")
    @Mapping(target = "accountRawData", expression = "java(JsonUtils.toJsonString(resource))")
    @Mapping(target = "openId", source = "resource.openId")
    void convert(ChirpMemberUtoolsLoginReqVO reqVo, UtoolsUserInfoResponse resource, @MappingTarget ChirpMemberAccountDO memberAccount);
}
