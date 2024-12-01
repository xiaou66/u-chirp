package u.chirp.application.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import u.boot.start.common.exception.NoDataException;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentChildrenListGetReqVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentListGetReqVO;
import u.chirp.application.product.controller.app.vo.ChirpProductPostCommentListRespVO;
import u.chirp.application.product.dal.dataobject.ChirpProductPostCommentDO;

import java.util.List;

/**
 * @author xiaou
 * @date 2024/11/18
 */
public interface IChirpProductPostCommentService extends IService<ChirpProductPostCommentDO> {

    List<Long> searchIdList(ChirpProductPostCommentListGetReqVO reqVo) throws NoDataException;

    ChirpProductPostCommentListRespVO payloadResult(ChirpProductPostCommentListGetReqVO reqVo, List<Long> commentIds);

    Long create(ChirpProductPostCommentDO productPostComment);

    List<Long> getChildrenIdsList(@Param("reqVo") ChirpProductPostCommentChildrenListGetReqVO reqVo) throws NoDataException;

    void updateTotalComment(Long parentCommentId);
}
