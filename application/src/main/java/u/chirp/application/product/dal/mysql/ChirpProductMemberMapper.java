package u.chirp.application.product.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import u.chirp.application.product.dal.dataobject.ChirpProductMemberDO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Mapper
public interface ChirpProductMemberMapper extends BaseMapper<ChirpProductMemberDO> {

    @Update("update chirp_product_member set thumbs_up_count = thumbs_up_count + 1 where product_id = #{productId} and member_id = #{memberId}")
    void addThumbsUpCount(@Param("productId") Long productId, @Param("memberId") Long memberId);
    @Update("update chirp_product_member set thumbs_up_count = thumbs_up_count - 1 where product_id = #{productId} and member_id = #{memberId}")
    void subThumbsUpCount(@Param("productId") Long productId, @Param("memberId") Long memberId);

    @Update("update chirp_product_member set follow_count = follow_count - 1 where product_id = #{productId} and member_id = #{memberId}")
    void subFollowCount(Long productId, Long memberId);
    @Update("update chirp_product_member set follow_count = follow_count + 1 where product_id = #{productId} and member_id = #{memberId}")
    void addFollowCount(Long productId, Long memberId);
}
