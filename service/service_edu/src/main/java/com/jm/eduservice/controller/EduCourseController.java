package com.jm.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jm.commonutils.R;
import com.jm.eduservice.entity.EduCourse;
import com.jm.eduservice.entity.vo.CourseInfoVo;
import com.jm.eduservice.entity.vo.CoursePublishVo;
import com.jm.eduservice.service.EduCourseService;
import com.jm.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        return R.ok().data("courseId", eduCourseService.saveCourseInfo(courseInfoVo));
    }

    //获取课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfo);
    }

    //更新课程基本信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程确认信息

    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo publishVo = eduCourseService.publishCourseInfo(id);
        return R.ok().data("publishCourse", publishVo);
    }

    //课程最终发布
    //修改课程状态

    @GetMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");              //设置课程发布状态
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    //删除课程信息
    @DeleteMapping("deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return R.ok();
    }

    //获取所以课程展示信息
    @GetMapping("getAllCourseInfo/{currentPage}/{limit}")
    public R getAllCourseInfo(@PathVariable long currentPage,
                              @PathVariable long limit){
        Page<EduCourse> page = new Page<>(currentPage, limit);
        IPage<EduCourse> data = eduCourseService.page(page, null);
        return R.ok().data("list", data.getRecords()).data("total", data.getTotal());
    }

}

