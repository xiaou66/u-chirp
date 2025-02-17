package u.chirp.application.product.convert;

import cn.dev33.satoken.stp.StpUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import u.chirp.application.product.controller.app.vo.AppProductPostListGetReqVO;
import u.chirp.application.product.controller.app.vo.AppProductPostSaveReqVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostGetRespVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostListRespVO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;
import u.chirp.application.product.service.bo.AppProductPostListBO;
import u.chirp.application.product.service.bo.ChirpProductPostListBO;
import u.chirp.application.product.service.bo.ProductPostBaseInfoBO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Mapper(imports = {StpUtil.class})
public interface ChirpProductPostConvert {
    ChirpProductPostConvert INSTANCE = Mappers.getMapper(ChirpProductPostConvert.class);

    @Mapping(target = "postFollowCount", ignore = true)
    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "postTop", ignore = true)
    @Mapping(target = "postThumbsUpCount", ignore = true)
    @Mapping(target = "postHot", ignore = true)
    @Mapping(target = "postHandleProgress", ignore = true)
    @Mapping(target = "postGood", ignore = true)
    @Mapping(target = "creator", expression = "java(StpUtil.getLoginIdAsLong())")
    @Mapping(target = "createTime", ignore = true)
    ChirpProductPostDO convert(AppProductPostSaveReqVO reqVo);

    ProductPostBaseInfoBO convertProductPostBaseInfoBO(ChirpProductPostDO chirpProductPost);

    @Mapping(target = "memberId", ignore = true)
    @Mapping(target = "joinCollectTable", ignore = true)
    @Mapping(target = "productId", ignore = true)
    AppProductPostListBO toAppProductPostListBO(AppProductPostListGetReqVO reqVo);

    List<ChirpProductPostListRespVO> toChirpProductPostListRespDO(List<ChirpProductPostListBO> res);

    ChirpProductPostListRespVO toChirpProductPostListRespDO(ChirpProductPostListBO res);


    @Mapping(target = "memberInfo", ignore = true)
    @Mapping(target = "fileList", ignore = true)
    ChirpProductPostListBO toChirpProductPostBO(ChirpProductPostDO productPostList);

    @Mapping(target = "memberInfo", ignore = true)
    @Mapping(target = "fileList", ignore = true)
    ChirpProductPostGetRespVO toChirpProductPostGetRespVO(ChirpProductPostDO productPostDO);
}
