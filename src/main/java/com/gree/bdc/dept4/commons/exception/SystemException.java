package com.gree.bdc.dept4.commons.exception;

/**
 * @author: 260222
 * @date: 2018/12/11 10:54
 * @description: 服务器异常
 */
public class SystemException extends RuntimeException {
    public SystemException(String message) {
        super(message);
    }
}
