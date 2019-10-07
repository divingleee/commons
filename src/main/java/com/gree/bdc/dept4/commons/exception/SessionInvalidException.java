package com.gree.bdc.dept4.commons.exception;

/**
 * @description: session 失效异常
 * @author: divingLee
 * @date: 2019/10/7 10:01
 */
public class SessionInvalidException extends RuntimeException {
    public SessionInvalidException(String message) {
        super(message);
    }
}
