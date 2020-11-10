package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2018/12/11 10:54
 * @description: 非法参数异常
 */
public class ParamsIllegalException extends RuntimeException {
    public ParamsIllegalException(String message) {
        super(message);
    }
}
