package u.chirp.application.product.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import u.chirp.application.product.dal.dataobject.ChirpProductDO;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@Mapper
public interface ChirpProductMapper extends BaseMapper<ChirpProductDO> {
}
