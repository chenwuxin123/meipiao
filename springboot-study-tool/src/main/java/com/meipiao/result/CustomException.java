package com.meipiao.result;

/**
 * @Author: Chenwx
 * @Date: 2020/5/9 15:37
 */
public class CustomException extends Exception {
    private Integer code;
    private String msg;

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public CustomException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
