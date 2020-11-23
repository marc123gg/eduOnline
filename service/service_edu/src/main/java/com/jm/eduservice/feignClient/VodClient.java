package com.jm.eduservice.feignClient;

import com.jm.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("service-vod")
public interface VodClient {

    @DeleteMapping("/eduvod/video/deleteAlyVideo/{videoId}")
    public R deleteAlyVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
