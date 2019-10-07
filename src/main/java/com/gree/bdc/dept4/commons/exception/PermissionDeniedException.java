package com.gree.bdc.dept4.commons.exception;

/**
 * @description: 权限不足异常
 * @author: divingLee
 * @date: 2019/10/7 10:01
 */
public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String message) {
        super(message);
    }
}
