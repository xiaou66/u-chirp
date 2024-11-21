package u.chirp.application.mumber.convert;

import cn.dev33.satoken.stp.StpUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import u.chirp.application.mumber.controller.admin.vo.SocialAccountListGetRespVO;
import u.chirp.application.mumber.controller.admin.vo.SocialAccountSaveReqVO;
import u.chirp.application.mumber.dal.dataobject.ChirpSocialAccountDO;

import java.util.List;


/**
 * @author xiaou
 * @date 2024/11/21
 */
@Mapper(imports = {StpUtil.class})
public interface ChirpSocialAccountConvert {
    ChirpSocialAccountConvert INSTANCE = Mappers.getMapper(ChirpSocialAccountConvert.class);

    @Mapping(target = "updater", expression = "java(StpUtil.getLoginIdAsLong())")
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "creator", expression = "java(reqVo.getSocialId() == null ? StpUtil.getLoginIdAsLong() : null)")
    @Mapping(target = "createTime", ignore = true)
    ChirpSocialAccountDO convert(SocialAccountSaveReqVO reqVo);

    List<SocialAccountListGetRespVO> convertSocialAccountListGetRespVO(List<ChirpSocialAccountDO> list);

    SocialAccountListGetRespVO convertSocialAccountListGetRespVO(ChirpSocialAccountDO list);
}
