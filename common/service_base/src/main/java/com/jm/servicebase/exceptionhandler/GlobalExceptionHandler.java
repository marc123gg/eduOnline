package com.jm.servicebase.exceptionhandler;



import com.jm.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常返回
 * @author marc
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //指定出现什么异常时执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.fail().msg("执行了全局异常处理");
    }
}
