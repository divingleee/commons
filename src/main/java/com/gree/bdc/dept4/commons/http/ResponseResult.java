package com.gree.bdc.dept4.commons.http;

import java.io.Serializable;

/**
 * @description: http 响应统一的返回类型
 * @author: divingLee
 * @date: 2019/10/7 9:05
 */
public class ResponseResult<T> implements Serializable {
    private Integer code;

    private String message;

    private T data;

    /**
     * 成功状态返回
     *
     * @return
     */
    public static ResponseResult success() {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    /**
     * 成功状态返回 带结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(T data) {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    /**
     * 失败状态返回
     *
     * @param code
     * @return
     */
    public static ResponseResult fail(ResponseCode code) {
        return fail(code, null);
    }

    public static <T> ResponseResult fail(Integer code, String message) {
        return fail(code, message, null);
    }

    public static <T> ResponseResult fail(ResponseCode code, T data) {
        return fail(code.getCode(), code.getMessage(), data);
    }

    public static <T> ResponseResult fail(Integer code, String message, T data) {
        return new ResponseResult(code, message, data);
    }


    public ResponseResult() {
    }

    private ResponseResult(Integer code, String message) {
        this(code, message, null);
    }

    private ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
