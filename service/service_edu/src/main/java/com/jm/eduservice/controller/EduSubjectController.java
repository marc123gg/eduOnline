package com.jm.eduservice.controller;


import com.jm.commonutils.R;
import com.jm.eduservice.entity.EduSubject;
import com.jm.eduservice.service.EduSubjectService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-11-12
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin  //解决跨域问题（协议如http与https就是不同 ip 端口， 三个条件中有一个不同就算跨域）
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubjectByExcel(MultipartFile file) throws IOException {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }

    @GetMapping("getOneSubject")
    public R getOneSubject(){
        return R.ok().data("subjectOneList", eduSubjectService.getOneSubject());
    }

    @GetMapping("subjectList")
    public R getSubjectList(){
        List<EduSubject> list = eduSubjectService.list(null);
        List<Map<String, Object>> result = new ArrayList<>();
        for(EduSubject par : list){
            //为一级分类
            if("0".equals(par.getParentId())){
                Map<String, Object> map = new HashMap<>();
                List<Map<String, String>> children = new ArrayList<>();
                map.put("id", par.getId());
                map.put("label", par.getTitle());
                for(EduSubject second : list){
                    if(second.getParentId().equals(par.getId())){
                        Map<String, String> childInfo = new HashMap<>();
                        childInfo.put("id", second.getId());
                        childInfo.put("label", second.getTitle());
                        children.add(childInfo);
                    }
                }
                map.put("children", children);
                result.add(map);
            }
        }
        return R.ok().data("list", result);
    }

}

