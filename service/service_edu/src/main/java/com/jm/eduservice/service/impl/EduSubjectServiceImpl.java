package com.jm.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jm.eduservice.entity.EduSubject;
import com.jm.eduservice.entity.excel.SubjectData;
import com.jm.eduservice.listener.SubjectExcelListener;
import com.jm.eduservice.mapper.EduSubjectMapper;
import com.jm.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author marc
 * @since 2020-11-12
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService){
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
    @Override
    public List<EduSubject> getOneSubject(){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        return baseMapper.selectList(wrapper);
    }
}
