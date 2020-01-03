package com.example.demo.base;

/**
 * @author yef
 * @date 2019/8/8
 */
public class ResponseMessage {
    private int code;
    private String message;
    private Object data;
    private ResponseMessage(){

    }
    public static ResponseMessage ofSuccess(Object data){
        return new ResponseMessage(Status.SUCCESS.getCode(),Status.SUCCESS.getMessage(),data);
    }
    public static ResponseMessage ofStatus(Status status){
        return new ResponseMessage(status.getCode(),status.getMessage(),null);
    }
    public ResponseMessage(int code,String message,Object data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
