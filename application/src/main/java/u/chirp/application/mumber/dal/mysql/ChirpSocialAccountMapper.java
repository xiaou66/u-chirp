package u.chirp.application.mumber.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import u.chirp.application.mumber.dal.dataobject.ChirpSocialAccountDO;

/**
 * @author xiaou
 * @date 2024/11/20
 */
@Mapper
public interface ChirpSocialAccountMapper extends BaseMapper<ChirpSocialAccountDO> {
}
