package u.chirp.application.product.convert;

import cn.dev33.satoken.stp.StpUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Mapper(imports = {StpUtil.class})
public interface ChirpProductPostConvert {
    ChirpProductPostConvert INSTANCE = Mappers.getMapper(ChirpProductPostConvert.class);

    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "postTop", ignore = true)
    @Mapping(target = "postThumbsUpCount", ignore = true)
    @Mapping(target = "postHot", ignore = true)
    @Mapping(target = "postHandleProgress", ignore = true)
    @Mapping(target = "postGood", ignore = true)
    @Mapping(target = "postCollectCount", ignore = true)
    @Mapping(target = "creator", expression = "java(StpUtil.getLoginIdAsLong())")
    @Mapping(target = "createTime", ignore = true)
    ChirpProductPostDO convert(AppProductPostSaveReqVO reqVo);
}
