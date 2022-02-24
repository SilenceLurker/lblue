package com.cn.emio.sl.lblue.test.error;

/**
 * @author Silence_Lurker
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
