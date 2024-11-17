package u.boot.start.web.api;


import lombok.Data;

/**
 * @author xiaou
 * @date 2024/11/17
 */
@Data
public class Api {
    /**
     * API 前缀，实现所有 Controller 提供的 RESTFul API 的统一前缀
     *
     *
     * 意义：通过该前缀，避免 Swagger、Actuator 意外通过 Nginx 暴露出来给外部，带来安全性问题
     *      这样，Nginx 只需要配置转发到 /api/* 的所有接口即可。
     *
     */
    private String prefix;

    /**
     * Controller 所在包的 Ant 路径规则
     *
     * 主要目的是，给该 Controller 设置指定的 {@link #prefix}
     */
    private String controller;

    public Api(String prefix, String controller) {
        this.prefix = prefix;
        this.controller = controller;
    }
}
