package u.chirp.application.mumber.service.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xiaou
 * @date 2024/11/21
 */
@Data
@Accessors(chain = true)
public class MemberLoginSuccessInfoBO {
    private Long memberId;
    private String token;
}
