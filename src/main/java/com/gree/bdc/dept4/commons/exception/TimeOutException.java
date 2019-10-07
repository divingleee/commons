package com.gree.bdc.dept4.commons.exception;

/**
 * @description: 超时异常
 * @author: divingLee
 * @date: 2019/10/7 10:01
 */
public class TimeOutException extends RuntimeException {
    public TimeOutException(String message) {
        super(message);
    }
}
