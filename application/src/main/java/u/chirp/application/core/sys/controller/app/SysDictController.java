package u.chirp.application.core.sys.controller.app;

import cn.dev33.satoken.annotation.SaIgnore;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import u.boot.start.common.pojo.R;
import u.chirp.application.core.sys.controller.app.vo.DictListGetReqVO;
import u.chirp.application.core.sys.controller.app.vo.DictListGetRespVO;
import u.chirp.application.core.sys.convert.SystemDictDataConvert;
import u.chirp.application.core.sys.dal.dataobject.SystemDictDataDO;
import u.chirp.application.core.sys.dal.dataobject.SystemDictTypeDO;
import u.chirp.application.core.sys.service.ISystemDictDataService;

import java.util.List;

/**
 * @folder app/字典
 * @author xiaou
 * @date 2024/11/24
 */
@RestController
@SaIgnore
@RequestMapping("dict")
@Validated
public class SysDictController {

    @Resource
    private ISystemDictDataService systemDictDataService;

    /**
     * 获取字典列表
     * @param reqVo
     * @return
     * @tags v1.0.0
     */
    @RequestMapping("getDictList")
    public R<List<DictListGetRespVO>> getDictList(@Validated DictListGetReqVO reqVo) {
        List<SystemDictDataDO> systemDictDataList = systemDictDataService.list(Wrappers.lambdaQuery(SystemDictDataDO.class)
                .eq(SystemDictDataDO::getDictType, reqVo.getDictType())
                .orderByAsc(SystemDictDataDO::getSort));
        return R.success(SystemDictDataConvert.INSTANCE.toDictListGetRespVO(systemDictDataList));
    }
}
