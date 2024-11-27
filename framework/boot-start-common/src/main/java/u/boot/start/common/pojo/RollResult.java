package u.boot.start.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/27
 */
@Data
@AllArgsConstructor
public class RollResult<T> implements Serializable {
    /**
     * 分页结果
     */
    private List<T> list;

    /**
     * 下一个
     */
    private Long next;

    public static<T> RollResult<T> empty() {
        return new RollResult<T>(Collections.emptyList(), null);
    }
}
