package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2019/10/7 10:01
 * @description: 授权失败异常
 */
public class AuthorizedFailException extends RuntimeException {
    public AuthorizedFailException(String message) {
        super(message);
    }
}
