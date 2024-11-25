package u.chirp.application.mumber.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import u.chirp.application.mumber.controller.app.vo.MemberInfoGetRespVO;
import u.chirp.application.mumber.dal.dataobject.ChirpMemberDO;
import u.chirp.application.mumber.service.bo.ChirpMemberBaseInfoBO;
import u.chirp.application.mumber.service.bo.GenerateMemberBO;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Mapper
public interface ChirpMemberConvert {
    ChirpMemberConvert INSTANCE = Mappers.getMapper(ChirpMemberConvert.class);

    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "mobile", ignore = true)
    @Mapping(target = "memberStatus", ignore = true)
    @Mapping(target = "memberPassword", ignore = true)
    @Mapping(target = "memberId", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    ChirpMemberDO convert(GenerateMemberBO generateMemberBO);

    MemberInfoGetRespVO toProductMemberInfoGetReqVO(ChirpMemberDO chirpMember);

    ChirpMemberBaseInfoBO toChirpMemberBaseInfoBO(ChirpMemberDO chirpMember);
}
