package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2019/10/7 10:01
 * @description: session 失效异常
 */
public class SessionInvalidException extends RuntimeException {
    public SessionInvalidException(String message) {
        super(message);
    }
}
