package com.jm.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author marc
 * excel实体类 用于定义表头数据
 */

public class SubjectData {
    @ExcelProperty(index = 0)
    private String firstSubjectName;
    @ExcelProperty(index = 1)
    private String secondSubjectName;

    public String getFirstSubjectName(){
        return firstSubjectName;
    }
    public String getSecondSubjectName(){
        return secondSubjectName;
    }
    public void setFirstSubjectName(String firstSubjectName){
        this.firstSubjectName = firstSubjectName;
    }
    public void setSecondSubjectName(String secondSubjectName){
        this.secondSubjectName = secondSubjectName;
    }
}
