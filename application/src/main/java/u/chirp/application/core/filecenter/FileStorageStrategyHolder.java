package u.chirp.application.core.filecenter;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Component
public class FileStorageStrategyHolder {
    @Resource
    private Map<String, IFileStorageStrategy> handlerMap = new HashMap<>();


    @Autowired
    public void setHandler(List<IFileStorageStrategy> handlerList) {
        this.handlerMap = handlerList.stream()
                .collect(Collectors.toMap(IFileStorageStrategy::getUpdateSource,
                        Function.identity(),
                        (oldData, newData) -> newData));
    }

    public IFileStorageStrategy getHandler(String uploadSource) {
        return handlerMap.get(uploadSource);
    }
}
