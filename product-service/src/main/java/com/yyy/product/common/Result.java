package com.yyy.product.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> vo = new Result<>();
        vo.setCode(200);
        vo.setMsg("success");
        vo.setData(data);
        return vo;
    }

    public static <T> Result<T> fail(String msg) {
        Result<T> vo = new Result<>();
        vo.setCode(500);
        vo.setMsg(msg);
        return vo;
    }
}
