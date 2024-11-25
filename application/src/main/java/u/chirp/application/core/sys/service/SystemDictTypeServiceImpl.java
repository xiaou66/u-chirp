package u.chirp.application.core.sys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.core.sys.dal.dataobject.SystemDictTypeDO;
import u.chirp.application.core.sys.dal.mysql.SystemDictTypeMapper;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Service
public class SystemDictTypeServiceImpl extends ServiceImpl<SystemDictTypeMapper, SystemDictTypeDO>
        implements ISystemDictTypeService{
}
