package com.jm.eduservice.controller;


import com.jm.commonutils.R;
import com.jm.eduservice.entity.EduVideo;
import com.jm.eduservice.feignClient.VodClient;
import com.jm.eduservice.service.EduVideoService;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private VodClient vodClient;

//    @Autowired
//    private com.jm.vod.service.vodService vodService;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())){
            vodClient.deleteAlyVideo(eduVideo.getVideoSourceId());
        }
        videoService.removeById(videoId);
        return R.ok();
    }

    @GetMapping("getVideoAlyId/{videoId}")
    public R getVideoAlyId(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("sourceId", eduVideo.getVideoSourceId());
    }

    //根据小节id获取信息
    @GetMapping("getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        EduVideo video = videoService.getById(videoId);
        Map<String, Object> map = new HashMap<>();
        map.put("title", video.getTitle());
        map.put("sort", video.getSort());
        map.put("free", video.getIsFree());
        map.put("videoSourceId", video.getVideoSourceId());
        map.put("videoOriginalName", video.getVideoOriginalName());
        return R.ok().data(map);
    }

    /**
     * 更新
     */
    @PostMapping("updateVideo")
    public R updateVideo(EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }


}

