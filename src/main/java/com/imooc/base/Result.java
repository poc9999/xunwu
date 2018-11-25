package com.imooc.base;

import com.imooc.base.XunWuEnum.ResultEnum;

/**
 * Company      : Shenzhen Greatonce Co Ltd
 * Created By   : Administrator
 * Created Date : 2018/11/15 18:57
 * Description  : 返回基础类
 */
public class Result extends BaseInfo {
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**'
     * 数据实体
     */
    private Object data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
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

    public static Result success(Object object){
        return new Result(200,"ok",object);
    }
    public static Result fail(int code,String message){
        return new Result(code,message);
    }
    public static Result fail(ResultEnum resultEnum){
        return new Result(resultEnum.getCode(),resultEnum.getMessage());
    }

}
