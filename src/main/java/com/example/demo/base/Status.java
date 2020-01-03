package com.example.demo.base;

/**
 * @author yef
 * @date 2019/8/8
 */
public enum Status {
    SUCCESS(200,"ok"),INVALID_PARAM(400,"invalid parameter"),
    ACCESS_DENY(401,"access deny");
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
