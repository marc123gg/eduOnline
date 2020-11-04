package com.jm.commonutils;

import org.springframework.http.HttpStatus;

public enum ResultCode {

    /** 操作成功 */
    SUCCESS(20000, "OPERATION SUCCESS"),
    /** 操作失败 */
    FAIL(20001, "OPERATION FAIL");




    private String msg;
    private int code;

    private ResultCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取返回结果状态码
     * @return int
     */
    public int getCode(){
        return code;
    }

    /**
     * 获取返回结果信息
     * @return String
     */
    public String getMsg(){
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ResultCode getByValue(int code){
        for(ResultCode resultCode : values()){
            if(resultCode.getCode() == code){
                return resultCode;
            }
        }
        return null;
    }

}
