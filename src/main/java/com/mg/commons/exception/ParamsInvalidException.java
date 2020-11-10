package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2018/12/11 10:54
 * @description: 参数无效异常
 */
public class ParamsInvalidException extends RuntimeException {
    public ParamsInvalidException(String message) {
        super(message);
    }
}
