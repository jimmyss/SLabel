package com.jimmyss.slabel.component;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private Integer code;//消息码
    private String msg; // 信息
    private T data; // 数据

    public static <T> BaseResponse<T> success(T object) {
        BaseResponse<T> r = new BaseResponse<T>();
        r.data = object;
        r.code = 20000;
        return r;
    }

    public static <T> BaseResponse<T> success(String msg, T object){
        BaseResponse<T> r = new BaseResponse<T>();
        r.msg=msg;
        r.data = object;
        r.code = 20000;
        return r;
    }

    public static <T> BaseResponse<T> error(String msg) {
        var r = new BaseResponse();
        r.msg = msg;
        r.code = 50000;
        return r;
    }
    public static <T> BaseResponse<T> error(Integer code, String msg) {
        var r = new BaseResponse();
        r.msg = msg;
        r.code = code;
        return r;
    }

    public static <T> BaseResponse<T> error(String msg, T object){
        var r=new BaseResponse();
        r.msg=msg;
        r.data=object;
        r.code=30000;
        return r;
    }

}
