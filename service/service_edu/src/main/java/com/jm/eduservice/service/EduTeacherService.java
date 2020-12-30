package com.jm.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jm.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author marc
 * @since 2020-11-02
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String,Object> getTeacherFrontList(Page<EduTeacher> teacherPage);
}
