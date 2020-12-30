package com.jm.servicebase.exceptionhandler;



import com.jm.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常返回
 * @author marc
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 指定出现什么异常时执行这个方法
     * @param e 异常参数
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.fail().msg("执行了全局异常处理");
    }

    @ExceptionHandler(JmDiyException.class)
    @ResponseBody
    public String error(JmDiyException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("flag", "false");
        map.put("code", e.getCode());
        map.put("msg", e.getMsg());
        map.put("data", "{}");
        return map.toString();
    }

}
