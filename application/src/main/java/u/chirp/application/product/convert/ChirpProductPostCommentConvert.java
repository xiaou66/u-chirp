package u.chirp.application.product.convert;

import cn.dev33.satoken.stp.StpUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentCreateReqVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentItemVO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostCommentDO;

/**
 * @author xiaou
 * @date 2024/11/30
 */
@Mapper(imports = {StpUtil.class})
public interface ChirpProductPostCommentConvert {
    ChirpProductPostCommentConvert INSTANCE = Mappers.getMapper(ChirpProductPostCommentConvert.class);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "fileList", ignore = true)
    @Mapping(target = "commenterInfo", ignore = true)
    @Mapping(target = "beCommenterInfo", ignore = true)
    ChirpProductPostCommentItemVO toChirpProductPostCommentItemVO(ChirpProductPostCommentDO productPostComment);

    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "replyMemberId", ignore = true)
    @Mapping(target = "creator", expression = "java(StpUtil.getLoginIdAsLong())")
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "commentTop", ignore = true)
    @Mapping(target = "commentThumbsUpCount", ignore = true)
    @Mapping(target = "commentId", ignore = true)
    ChirpProductPostCommentDO convert(ChirpProductPostCommentCreateReqVO reqVo);
}
