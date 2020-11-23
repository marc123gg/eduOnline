package com.jm.eduservice.service;

import com.jm.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jm.eduservice.service.impl.EduSubjectServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author marc
 * @since 2020-11-12
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 添加课程分类
     * @param file
     */
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<EduSubject> getOneSubject();
}
