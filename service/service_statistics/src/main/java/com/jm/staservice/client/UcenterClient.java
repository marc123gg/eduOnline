package com.jm.staservice.client;

import com.jm.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //查询某一天的注册人数
    @GetMapping("/educenter/member/countRegister/{date}")
    public R countRegister(@PathVariable("date") String date);
}
