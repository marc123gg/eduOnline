package com.jm.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jm.eduservice.entity.EduChapter;
import com.jm.eduservice.entity.EduVideo;
import com.jm.eduservice.entity.chapter.ChapterVo;
import com.jm.eduservice.entity.chapter.VideoVo;
import com.jm.eduservice.mapper.EduChapterMapper;
import com.jm.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jm.eduservice.service.EduVideoService;
import com.jm.servicebase.exceptionhandler.JmDiyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程章节 服务实现类
 * </p>
 *
 * @author marc
 * @since 2020-11-14
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询所有章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapperChapter);

        //2.根据课程id查询里面的所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideos = videoService.list(wrapperVideo);

        //创建list集合，用于封装最终数据
        List<ChapterVo> result = new ArrayList<>();
        for (EduChapter chapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            result.add(chapterVo);
            //创建集合 用于封装章节中的小节
            List<VideoVo> videoVoList = new ArrayList<>();
            for (EduVideo video : eduVideos) {
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return result;
    }

    /**
     * 删除章节
     */
    @Override
    public boolean deleteChapter(String chapterId){
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        //该章节存在小节不能删除
        if(count > 0){
            throw new JmDiyException(20001, "存在小节，不能删除");
        }
        else {            // 不存在小节可以删除
            int result = baseMapper.deleteById(chapterId);
            //成功 1>0 否0>0
            return result > 0;
        }
    }

    //根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId){
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }

}
