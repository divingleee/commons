package com.gree.bdc.dept4.commons.exception;

/**
 * @author: 260222
 * @date: 2018/12/11 10:54
 * @description: 非法参数异常
 */
public class ParamsIllegalException extends RuntimeException {
    public ParamsIllegalException(String message) {
        super(message);
    }
}
