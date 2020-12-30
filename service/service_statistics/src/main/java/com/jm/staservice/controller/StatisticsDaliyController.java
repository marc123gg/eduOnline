package com.jm.staservice.controller;


import com.jm.commonutils.R;
import com.jm.staservice.service.StatisticsDaliyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-12-12
 */
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDaliyController {

    @Autowired
    private StatisticsDaliyService statisticsDaliyService;

    //统计某一天的注册人数
    @PostMapping("registerCount/{date}")
    public R registerCount(@PathVariable String date){
        statisticsDaliyService.registerCount(date);
        return R.ok();
    }

    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type, @PathVariable String begin, @PathVariable String end){
        Map<String, Object> map = statisticsDaliyService.getShowData(type, begin, end);
        return R.ok().data(map);
    }
}

