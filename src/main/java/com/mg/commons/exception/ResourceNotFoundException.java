package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2018/12/11 14:03
 * @description: 资源不存在异常
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
