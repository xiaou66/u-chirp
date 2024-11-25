package u.chirp.application.core.sys.controller.app.vo;

import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/24
 */
@Data
public class DictListGetRespVO {
    /**
     * 字典排序
     * 不能为空，默认值为 0
     */
    private Integer sort;

    /**
     * 字典标签
     * 不能为空，默认值为 ''
     */
    private String label;

    /**
     * 字典键值
     * 不能为空，默认值为 ''
     */
    private String value;

    /**
     * 状态
     * 0 表示正常，1 表示停用，默认值为 0
     */
    private Byte status;

    /**
     * 颜色类型
     * 可为空，默认值为 ''
     */
    private String colorType;

    /**
     * CSS 样式
     * 可为空，默认值为 ''
     */
    private String cssClass;

    /**
     * 元数据
     * 可为空
     */
    private String meta;
}
