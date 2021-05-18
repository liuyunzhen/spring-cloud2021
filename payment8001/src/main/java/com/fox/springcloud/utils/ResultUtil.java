package com.fox.springcloud.utils;

import com.fox.springcloud.entity.CommonResult;

public class ResultUtil {
    public static CommonResult<Object> success(Object o){
        return new CommonResult<>(200, "操作成功", o);
    }

    public static CommonResult<Object> success(String message, Object o){
        return new CommonResult<>(200, message, o);
    }

    public static CommonResult<Object> build(int code, String message, Object o){
        return new CommonResult<>(code, message, o);
    }
}
