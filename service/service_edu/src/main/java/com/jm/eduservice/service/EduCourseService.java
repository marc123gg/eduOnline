package com.jm.eduservice.service;

import com.jm.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jm.eduservice.entity.vo.CourseInfoVo;
import com.jm.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author marc
 * @since 2020-11-14
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    void removeCourse(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

}