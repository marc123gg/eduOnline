package com.jm.eduservice.service;

import com.jm.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author marc
 * @since 2020-11-16
 */
public interface EduVideoService extends IService<EduVideo> {
    //根据课程id删除小节
    void removeVideoByCourseId(String courseId);

}
