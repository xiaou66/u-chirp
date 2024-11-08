package u.chirp.application.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.system.dal.dataobject.SysUserDO;
import u.chirp.application.system.dal.mysql.SysUserMapper;

/**
 * @author xiaou
 * @date 2024/11/7
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO>
        implements ISysUserService {
}
