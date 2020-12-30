package com.jm.staservice.service;

import com.jm.staservice.entity.StatisticsDaliy;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author marc
 * @since 2020-12-12
 */
public interface StatisticsDaliyService extends IService<StatisticsDaliy> {

    void registerCount(String date);

    Map<String, Object> getShowData(String type, String begin, String end);
}
