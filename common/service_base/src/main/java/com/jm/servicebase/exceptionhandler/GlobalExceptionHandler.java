package com.jm.servicebase.exceptionhandler;



import com.jm.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常返回
 * @author marc
 */
@ControllerAdvice
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
    public R error(JmDiyException e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.fail().code(e.getCode()).msg(e.getMsg());
    }

}
