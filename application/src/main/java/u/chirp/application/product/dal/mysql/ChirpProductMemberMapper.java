package u.chirp.application.product.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import u.chirp.application.product.dal.dataobject.ChirpProductMemberDO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Mapper
public interface ChirpProductMemberMapper extends BaseMapper<ChirpProductMemberDO> {

}
