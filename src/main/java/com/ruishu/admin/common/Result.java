package com.ruishu.admin.common;

import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result ok(Object data) {
        Result r = new Result();
        r.code = 200;
        r.message = "success";
        r.data = data;
        return r;
    }

    public static Result fail(String message) {
        Result r = new Result();
        r.code = 500;
        r.message = message;
        return r;
    }
}