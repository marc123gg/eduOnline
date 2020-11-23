package com.jm.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author marc
 * 自定义异常类
 */
@Data
@AllArgsConstructor   //生成有参数构造方法
@NoArgsConstructor    //生成无参数构造方法
public class JmDiyException extends RuntimeException {
    //状态码
    private Integer code;
    //异常信息
    private String msg;
}
