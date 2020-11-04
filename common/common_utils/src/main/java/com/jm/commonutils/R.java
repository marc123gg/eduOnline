package com.jm.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    private R(){

    }

    @ApiModelProperty("是否成功")
    private Boolean flag;

    @ApiModelProperty("返回码")
    private Integer code;

    @ApiModelProperty("返回消息")
    private String msg;

    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    public static R ok(){
        R r = new R();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMsg(ResultCode.SUCCESS.getMsg());
        r.setFlag(true);
        return r;
    }

    public static R fail(){
        R r = new R();
        r.setCode(ResultCode.FAIL.getCode());
        r.setMsg(ResultCode.FAIL.getMsg());
        r.setFlag(false);
        return r;
    }

    public R msg(String msg){
        this.setMsg(msg);
        return this;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
