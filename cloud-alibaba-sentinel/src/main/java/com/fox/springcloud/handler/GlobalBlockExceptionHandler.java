package com.fox.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fox.springcloud.entity.CommonResult;

public class GlobalBlockExceptionHandler {

    public static CommonResult testA_handler(BlockException exception){
        return new CommonResult(500, exception.getLocalizedMessage() + "==testA服务不可用");
    }

    public static CommonResult testC_handler(BlockException exception){
        return new CommonResult(500, exception.getLocalizedMessage() + "==testC服务不可用");
    }
}
