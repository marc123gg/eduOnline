package com.jm.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jm.eduservice.entity.EduSubject;
import com.jm.eduservice.entity.excel.SubjectData;
import com.jm.eduservice.service.EduSubjectService;
import com.jm.servicebase.exceptionhandler.JmDiyException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;
    }
    //监听读取的每一行
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new JmDiyException(20001, "文件数据为空");
        }
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getFirstSubjectName());
        //说明不存在该一级分类，则进行添加
        if(existOneSubject == null){
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getFirstSubjectName());
            System.out.println(subjectData.getFirstSubjectName());
            eduSubjectService.save(existOneSubject);
        }
        //获取一级分类的id值
        String pid = existOneSubject.getId();

        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getSecondSubjectName(), pid);
        if(existTwoSubject == null){
            existTwoSubject = new EduSubject();
            System.out.println("**************************" + subjectData.getSecondSubjectName());
            System.out.println(subjectData);
            existTwoSubject.setTitle(subjectData.getSecondSubjectName());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }

    }

    /**
     * 判断一级分类不能重复添加
     * @param subjectService    数据库服务注入
     * @param name              分类名
     * @return                  结果实体
     */
    private EduSubject existOneSubject(EduSubjectService subjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", 0);
        return subjectService.getOne(wrapper);
    }

    /**
     * 判断二级分类不能重复添加
     * @param eduSubjectService 数据库服务注入
     * @param name              分类名
     * @param pid               一级分类id
     * @return                  查询实体
     */
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return eduSubjectService.getOne(wrapper);
    }


    /**
     *  读取完成
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
