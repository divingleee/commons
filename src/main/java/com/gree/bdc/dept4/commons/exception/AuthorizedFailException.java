package com.gree.bdc.dept4.commons.exception;

/**
 * @description: 授权失败异常
 * @author: divingLee
 * @date: 2019/10/7 10:01
 */
public class AuthorizedFailException extends RuntimeException {
    public AuthorizedFailException(String message) {
        super(message);
    }
}
