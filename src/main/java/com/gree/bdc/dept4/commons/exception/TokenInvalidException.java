package com.gree.bdc.dept4.commons.exception;

/**
 * @description: token 失效异常
 * @author: divingLee
 * @date: 2019/10/7 10:01
 */
public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message) {
        super(message);
    }
}
