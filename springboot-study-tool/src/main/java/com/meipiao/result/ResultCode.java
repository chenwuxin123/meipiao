package com.meipiao.result;

/**
 * @Author: Chenwx
 * @Date: 2020/5/8 17:08
 */
public enum ResultCode {
    SUCCESS(200, "操作成功", true),
    FAILED(1001, "操作失败"),
    VALIDATE_FAILED(1002, "参数校验失败"),
    NULL(1003, "数据不存在"),
    ERROR(5000, "未知错误");

    private Integer code;
    private String msg;
    private boolean success;

    private ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success = false;
    }

    private ResultCode(Integer code, String msg, boolean success) {
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public boolean isSuccess() {
        return this.success;
    }
}