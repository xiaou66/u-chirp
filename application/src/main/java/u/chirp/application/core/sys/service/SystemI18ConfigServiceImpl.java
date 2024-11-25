package u.chirp.application.core.sys.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.core.sys.dal.dataobject.SystemI18ConfigDO;
import u.chirp.application.core.sys.dal.mysql.SystemI18ConfigMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Service
public class SystemI18ConfigServiceImpl extends ServiceImpl<SystemI18ConfigMapper, SystemI18ConfigDO>
        implements ISystemI18ConfigService {

    /**
     * 获取 I18Config
     * @param language
     * @return
     */
    @Override
    public Map<String, Object> getI18ConfigService(String language) {
        List<SystemI18ConfigDO> list = list(Wrappers.lambdaQuery(SystemI18ConfigDO.class)
                .and((wrapper) ->
                        wrapper.eq(SystemI18ConfigDO::getLanguage, language)
                                .or()
                                .isNull(SystemI18ConfigDO::getLanguage)));

        List<SystemI18ConfigDO> root = list.stream()
                .filter(item -> Objects.equals(item.getParentId(), 0L))
                .toList();
        Map<String, Object> res = new HashMap<>();
        buildI18Config(list, root, res);
        return res;
    }

    private void buildI18Config(List<SystemI18ConfigDO> list, List<SystemI18ConfigDO> nodeList, Map<String, Object> res) {
        for (SystemI18ConfigDO systemI18Config : nodeList) {
            if (Objects.equals(systemI18Config.getNodeType(), 2)) {
                // 父节点处理
                Map<String, Object> sub = new HashMap<>();
                res.put(systemI18Config.getName(), sub);
                List<SystemI18ConfigDO> subNode = list.stream()
                        .filter(item -> Objects.equals(item.getParentId(), systemI18Config.getId()))
                        .toList();
                buildI18Config(list, subNode, sub);
            } else {
                res.put(systemI18Config.getName(), systemI18Config.getValue());
            }
        }
    }
}
