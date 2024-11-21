package u.chirp.application.core.filecenter.local.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
public class FilePreviewReqVO {
    /**
     * key
     */
    @NotBlank(message = "key 不能为空")
    private String key;
}
