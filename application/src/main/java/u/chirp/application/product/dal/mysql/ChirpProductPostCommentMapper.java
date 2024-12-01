package u.chirp.application.product.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentChildrenListGetReqVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentListGetReqVO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostCommentDO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostDO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/18
 */
@Mapper
public interface ChirpProductPostCommentMapper extends BaseMapper<ChirpProductPostCommentDO> {

    List<Long> searchIdList(@Param("reqVo") ChirpProductPostCommentListGetReqVO reqVo);

    List<ChirpProductPostCommentDO> searchSubCommentList(@Param("commentIds") List<Long> commentIds);

    List<Long> getChildrenIdsList(@Param("reqVo") ChirpProductPostCommentChildrenListGetReqVO reqVo);
}
