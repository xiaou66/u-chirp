package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
     * 添加点赞数
     * @param productId
     * @param memberId
     */
    void addThumbsUpCount(Long productId, Long memberId);
    /**
     * 减少点赞数
     * @param productId
     * @param memberId
     */
    void subThumbsUpCount(Long productId, Long memberId);
    /**
     * 添加收集数
     * @param productId
     * @param memberId
     */
    void addFollowCount(Long productId, Long memberId);
    /**
     * 减少收集数
     * @param productId
     * @param memberId
     */
    void subFollowCount(Long productId, Long memberId);

    void createMemberAccount(Long memberId, Long productId);
}
