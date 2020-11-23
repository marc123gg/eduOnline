package com.jm.demo.excel;

import com.alibaba.excel.EasyExcel;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args){
        //设置写入文件夹地址和excel文件名称
        String fileName = "E:\\01.xlsx";

       // EasyExcel.write(fileName, DataTest.class).sheet("studentList").doWrite(getData());
        EasyExcel.read(fileName, DataTest.class, new ExcelListener()).sheet().doRead();
    }

    private static List<DataTest> getData(){
        List<DataTest> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            DataTest dataTest = new DataTest();
            dataTest.setSno(String.valueOf(i));
            dataTest.setName("marc" + i);
            list.add(dataTest);
        }
        return list;
    }

}
