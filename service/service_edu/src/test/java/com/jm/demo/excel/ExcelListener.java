package com.jm.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<DataTest> {
    //监听一行一行读取
    @Override
    public void invoke(DataTest dataTest, AnalysisContext analysisContext) {
        System.out.println(dataTest.getSno() + "name is : " + dataTest.getName());
    }
    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    //读取表头


    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头: " + headMap);
    }
}
