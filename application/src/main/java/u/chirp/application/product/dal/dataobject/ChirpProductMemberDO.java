package u.chirp.application.product.dal.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import u.boot.start.db.mysql.BaseDO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Mapper
@TableName("chirp_product_member")
@Data
public class ChirpProductMemberDO extends BaseDO<ChirpProductMemberDO> {
    /**
     * 会员 id
     */
    @TableId("member_id")
    private Long memberId;

    /**
     * 产品主键 id
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 问题数
     */
    @TableField("problem_count")
    private Long problemCount;

    /**
     * 点赞数
     */
    @TableField("thumbs_up_count")
    private Long thumbsUpCount;
}
