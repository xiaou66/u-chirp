package u.chirp.application.product.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import u.chirp.application.product.controller.admin.vo.ChirpProductItemRespVO;
import u.chirp.application.product.dal.dataobject.ChirpProductDO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Mapper
public interface ChirpProductConvert {
    ChirpProductConvert INSTANCE = Mappers.getMapper(ChirpProductConvert.class);

    List<ChirpProductItemRespVO> convert(List<ChirpProductDO> list);

    ChirpProductItemRespVO convert(ChirpProductDO list);
}
