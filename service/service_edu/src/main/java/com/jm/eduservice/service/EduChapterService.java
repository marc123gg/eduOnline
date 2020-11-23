package com.jm.eduservice.service;

import com.jm.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jm.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程章节 服务类
 * </p>
 *
 * @author marc
 * @since 2020-11-14
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
