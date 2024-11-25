package u.chirp.application.core.sys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.core.sys.dal.dataobject.SystemDictDataDO;
import u.chirp.application.core.sys.dal.mysql.SystemDictDataMapper;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Service
public class SystemDictDataServiceImpl extends ServiceImpl<SystemDictDataMapper, SystemDictDataDO>
        implements ISystemDictDataService{
}
