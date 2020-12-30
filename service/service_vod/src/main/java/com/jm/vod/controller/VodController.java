package com.jm.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.jm.commonutils.R;
import com.jm.servicebase.exceptionhandler.JmDiyException;
import com.jm.vod.Utils.ConstantVodUtils;
import com.jm.vod.service.vodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class VodController {

    @Autowired
    private vodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file){
        String videoId = vodService.updateAlyVideo(file);
        return R.ok().data("videoId", videoId);
    }

    //根据视频id删除视频
    @DeleteMapping("deleteAlyVideo/{videoId}")
    public R deleteAlyVideo(@PathVariable String videoId){
        vodService.deleteVideoById(videoId);
        return R.ok();
    }

    //根据id列表批量删除
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeListAlyVideo(videoIdList);
        return R.ok();
    }

    //根据id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        DefaultAcsClient client = ConstantVodUtils.client;
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            response = client.getAcsResponse(request);
            //播放凭证
            return R.ok().data("playAuth",response.getPlayAuth());
            //VideoMeta信息
            //System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            return R.fail().msg(e.getLocalizedMessage());
        }
        //System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

}
