package com.jm.eduservice.controller;


import com.jm.commonutils.R;
import com.jm.eduservice.entity.EduChapter;
import com.jm.eduservice.entity.chapter.ChapterVo;
import com.jm.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程章节 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    //课程大纲列表
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("list", list);
    }

    /**
     *  添加章节
     */
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    /**
     * 删除章节
     */
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag = eduChapterService.deleteChapter(chapterId);
        return flag ? R.ok() : R.fail();
    }

    /**
     * 修改章节
     */
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }
}

