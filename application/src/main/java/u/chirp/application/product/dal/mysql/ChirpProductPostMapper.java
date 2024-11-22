package u.chirp.application.product.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;
import u.chirp.application.product.service.bo.AppProductPostListBO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Mapper
public interface ChirpProductPostMapper extends BaseMapper<ChirpProductPostDO> {
    @Update("update chirp_product_post set post_thumbs_up_count = post_thumbs_up_count + 1 where product_id = #{productId} and post_id = #{postId}")
    void thumbsUp(@Param("productId") Long productId, @Param("postId") Long postId);

    @Update("update chirp_product_post set post_thumbs_up_count = post_thumbs_up_count - 1 where product_id = #{productId} and post_id = #{postId}")
    void unThumbsUp(@Param("productId") Long productId, @Param("postId") Long postId);

    @Update("update chirp_product_post set post_collect_count = post_collect_count + 1 where product_id = #{productId} and post_id = #{postId}")
    void follow(@Param("productId") Long productId, @Param("postId") Long postId, @Param("collect") Boolean collect);

    @Update("update chirp_product_post set post_collect_count = post_collect_count - 1 where product_id = #{productId} and post_id = #{postId}")
    void unFollow(@Param("productId") Long productId, @Param("postId") Long postId, @Param("collect") Boolean collect);

    List<Long> searchIdList(@Param("page") IPage<?> page, @Param("bo") AppProductPostListBO bo);
}
