package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2019/10/7 10:01
 * @description: 服务调用超时异常
 */
public class TimeOutException extends RuntimeException {
    public TimeOutException(String message) {
        super(message);
    }
}
