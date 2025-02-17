package u.chirp.application.product.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import u.chirp.application.product.dal.dataobject.ChirpProductMemberDO;
import u.chirp.application.product.dal.mysql.ChirpProductMemberMapper;

import java.util.Objects;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Service
public class ChirpProductMemberServiceImpl extends ServiceImpl<ChirpProductMemberMapper, ChirpProductMemberDO>
        implements IChirpProductMemberService {


    @Override
    public void addPostCount(Long memberId, Long productId) {
        ChirpProductMemberDO chirpProductMemberDO = baseMapper.selectOne(Wrappers.lambdaQuery(ChirpProductMemberDO.class)
                .eq(ChirpProductMemberDO::getProductId, productId)
                .eq(ChirpProductMemberDO::getMemberId, memberId)
                .last("limit 1"));
        if (Objects.isNull(chirpProductMemberDO)) {
            chirpProductMemberDO = initChirpProductMember(memberId, productId);
        }
        chirpProductMemberDO.setProblemCount(chirpProductMemberDO.getProblemCount() + 1);
        saveOrUpdate(chirpProductMemberDO);
    }

    @Override
    public void addThumbsUpCount(Long productId, Long memberId) {
        baseMapper.addThumbsUpCount(productId, memberId);
    }

    @Override
    public void subThumbsUpCount(Long productId, Long memberId) {
        baseMapper.subThumbsUpCount(productId, memberId);
    }

    @Override
    public void addFollowCount(Long productId, Long memberId) {
        baseMapper.addFollowCount(productId, memberId);
    }

    @Override
    public void subFollowCount(Long productId, Long memberId) {
        baseMapper.subFollowCount(productId, memberId);
    }

    @Override
    public void createMemberAccount(Long memberId, Long productId) {
        ChirpProductMemberDO productMemberDO = new ChirpProductMemberDO();
        productMemberDO.setMemberId(memberId);
        productMemberDO.setProductId(productId);
        baseMapper.insert(productMemberDO);
    }

    private ChirpProductMemberDO initChirpProductMember(Long memberId, Long productId) {
        ChirpProductMemberDO chirpProductMemberDO = new ChirpProductMemberDO();
        chirpProductMemberDO.setMemberId(memberId);
        chirpProductMemberDO.setProductId(productId);
        chirpProductMemberDO.setProblemCount(0L);
        chirpProductMemberDO.setThumbsUpCount(0L);
        return chirpProductMemberDO;
    }
}
