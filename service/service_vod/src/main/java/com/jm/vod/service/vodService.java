package com.jm.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface vodService {

    //根据文件流上传视频到阿里云
    String updateAlyVideo(MultipartFile file);

    //根据视频id删除视频
    void deleteVideoById(String videoId);

    //批量删除
    void removeListAlyVideo(List<String> videoIdList);
}
