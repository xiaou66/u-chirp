package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.constraints.NotNull;
import u.chirp.application.product.dal.dataobject.ChirpProductMemberDO;

/**
 * @author xiaou
 * @date 2024/11/18
 */
public interface IChirpProductMemberService extends IService<ChirpProductMemberDO> {
    /**
     * 新增回帖数量
     *
     * @param productId
     * @param id
     */
    void addPostCount(Long memberId, Long productId);

    /**
     * 被点赞数
     * @param productId
     * @param creator
     */
    void addThumbsUpCount(Long productId, Long memberId);

    void subThumbsUpCount(Long productId, Long creator);
}
