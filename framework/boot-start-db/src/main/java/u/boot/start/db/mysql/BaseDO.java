package u.boot.start.db.mysql;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xiaou
 * @date 2024/11/7
 */
@Data
public class BaseDO<T extends Model<?>> extends Model<T> {

    /**
     * 更新人
     */
    private Long updater;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
