package com.jm.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jm.eduservice.entity.EduVideo;
import com.jm.eduservice.feignClient.VodClient;
import com.jm.eduservice.mapper.EduVideoMapper;
import com.jm.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author marc
 * @since 2020-11-16
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapperSelect = new QueryWrapper<>();
        wrapperSelect.select("video_source_id");
        wrapperSelect.eq("course_id", courseId);
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperSelect);
        //取出数组里的阿里云视频id
        List<String> ids = eduVideoList.stream().map(EduVideo::getVideoSourceId).collect(Collectors.toList());

        if(ids.size() > 0) {
            vodClient.deleteBatch(ids);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
