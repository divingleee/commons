package com.mg.commons.exception;

/**
 * @author: divinglee
 * @date: 2019/10/7 10:01
 * @description: 权限不足异常
 */
public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String message) {
        super(message);
    }
}
