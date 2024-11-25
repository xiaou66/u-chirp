package u.chirp.application.core.sys.controller.app.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Data
public class DictListGetReqVO {

    /**
     * 字典类型
     */
    @NotNull(message = "字典类型 不能为空")
    private String dictType;
}
