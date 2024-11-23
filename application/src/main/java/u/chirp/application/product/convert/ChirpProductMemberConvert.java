package u.chirp.application.product.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import u.boot.start.common.pojo.R;
import u.chirp.application.product.controller.app.vo.ProductMemberInfoGetRespVO;
import u.chirp.application.product.dal.dataobject.ChirpProductMemberDO;

/**
 * @author xiaou
 * @date 2024/11/23
 */
@Mapper
public interface ChirpProductMemberConvert {
    ChirpProductMemberConvert INSTANCE = Mappers.getMapper(ChirpProductMemberConvert.class);

    ProductMemberInfoGetRespVO convertProductMemberInfoGetRespVO(ChirpProductMemberDO productMember);
}
