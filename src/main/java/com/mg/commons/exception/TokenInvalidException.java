package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2019/10/7 10:01
 * @description: token 失效异常
 */
public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException(String message) {
        super(message);
    }
}
