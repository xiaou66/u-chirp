package u.chirp.application.core.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import u.chirp.application.core.sys.dal.dataobject.SystemI18ConfigDO;

import java.util.Map;

/**
 * @author xiaou
 * @date 2024/11/24
 */
public interface ISystemI18ConfigService extends IService<SystemI18ConfigDO> {
    /**
     * 获取 I18Config
     * @param language
     * @return
     */
    Map<String, Object> getI18ConfigService(String language);
}
