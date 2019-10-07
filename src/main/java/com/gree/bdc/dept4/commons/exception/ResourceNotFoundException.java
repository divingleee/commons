package com.gree.bdc.dept4.commons.exception;

/**
 * @author: 260222
 * @date: 2018/12/11 14:03
 * @description: 资源不存在异常
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
