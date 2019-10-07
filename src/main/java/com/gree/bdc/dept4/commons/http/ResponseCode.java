package com.gree.bdc.dept4.commons.http;

/**
 * @description: http 响应统一的内部响应码
 * @author: divingLee
 * @date: 2019/10/7 8:46
 */
public enum ResponseCode {
    SUCCESS(1000, "请求成功"),

    /**
     * 参数相关
     */
    PARAMS_INVALID(2001, "参数无效"),
    PARAMS_ILLEGAL(2002, "非法参数"),

    /**
     * 服务器相关
     */
    SYSTEM_FAIL(3001, "服务器繁忙"),

    /**
     * 权限校验相关
     */
    TOKEN_INVALID(4001, "token 失效"),
    SESSION_INVALID(4002, "session 失效"),
    AUTHORIZED_FAIL(4003, "授权失败"),
    PERMISSION_DENIED(4004, "权限不足"),

    /**
     * 业务相关
     */
    BUSINESS_FAIL(5001, "业务异常"),

    /**
     * 资源相关
     */
    RESOURCE_NOT_FOUND(6001, "资源不存在"),

    /**
     * 超时相关
     */
    CALL_TIME_OUT(7001, "服务调用超时异常"),
    ;


    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
