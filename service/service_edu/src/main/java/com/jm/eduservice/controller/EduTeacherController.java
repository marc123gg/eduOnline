package com.jm.eduservice.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jm.commonutils.R;
import com.jm.commonutils.ResultCode;
import com.jm.eduservice.entity.EduTeacher;
import com.jm.eduservice.entity.vo.TeacherQuery;
import com.jm.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
@Api(description = "讲师管理")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "获取所有讲师列表")
    @GetMapping("findAll")
    public R allTeacherList(){
        List<EduTeacher> teachers = eduTeacherService.list(null);
        if(teachers != null){
            return R.ok().data("items", teachers);
        }
        return R.fail();
    }

    /**
     * mybatis-plus3.3.0以下remove有bug，只要程序不报错不管id是否存在都会返回true
     * 3.3.0版本已修复
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public R deleteById(@PathVariable String id){
        return eduTeacherService.removeById(id) ? R.ok() : R.fail();
    }

    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pagingQuery(@PathVariable long current,
                         @PathVariable long limit){
        Page<EduTeacher> page = new Page<>(current, limit);
        IPage<EduTeacher> data = eduTeacherService.page(page, null);
        long total = data.getTotal();
        List<EduTeacher> list = data.getRecords();
        return R.ok().data("total", total).data("rows", list);
    }

    /**
     * @RequestBody只能用post请求
     */
    @ApiOperation(value = "条件分页查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (StrUtil.isNotEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (StrUtil.isNotEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (StrUtil.isNotEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        queryWrapper.orderByDesc("gmt_create");
        IPage<EduTeacher> data = eduTeacherService.page(page, queryWrapper);
        long total = data.getTotal();
        List<EduTeacher> list = data.getRecords();
        return R.ok().data("total", total).data("rows", list);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        return eduTeacherService.save(eduTeacher) ? R.ok() : R.fail();
     }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        return flag ? R.ok() : R.fail();
    }




}

