package u.boot.start.common.pojo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/27
 */
@Data
public class RollParam {
    /**
     * 页尺
     */
    private Integer pageSize = 10;
    /**
     * 游标
     */
    private Long next;
}
