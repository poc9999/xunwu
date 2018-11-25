package com.imooc.base.XunWuEnum;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/16 23:01
 * Description  : 返回信息枚举类
 */
public enum  ResultEnum {

    EXCEPTION(110,"请求异常");

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
