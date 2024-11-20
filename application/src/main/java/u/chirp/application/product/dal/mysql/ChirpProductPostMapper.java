package u.chirp.application.product.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Mapper
public interface ChirpProductPostMapper extends BaseMapper<ChirpProductPostDO> {
    @Update("update chirp_product_post set post_thumbs_up_count = post_thumbs_up_count + #{thumbsUp == true ? 1 : 0} where product_id = #{productId} and post_id = #{postId}")
    Integer thumbsUp(@Param("productId") Long productId, @Param("postId") Long postId, Boolean thumbsUp);
}
