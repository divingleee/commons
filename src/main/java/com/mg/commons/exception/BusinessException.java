package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2018/12/11 10:54
 * @description: 业务异常
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
