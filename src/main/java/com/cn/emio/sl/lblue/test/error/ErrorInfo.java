package com.cn.emio.sl.lblue.test.error;

/**
 * @author Silence_Lurker
 */
public class ErrorInfo<T> {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 100;

    private Integer code;

    private String message;
    private String url;
    private T data;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }
}
