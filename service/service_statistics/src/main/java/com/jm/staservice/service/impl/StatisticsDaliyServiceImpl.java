package com.jm.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jm.commonutils.R;
import com.jm.staservice.client.UcenterClient;
import com.jm.staservice.entity.StatisticsDaliy;
import com.jm.staservice.mapper.StatisticsDaliyMapper;
import com.jm.staservice.service.StatisticsDaliyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author marc
 * @since 2020-12-12
 */
@Service
public class StatisticsDaliyServiceImpl extends ServiceImpl<StatisticsDaliyMapper, StatisticsDaliy> implements StatisticsDaliyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void registerCount(String date) {

        QueryWrapper<StatisticsDaliy> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", date);
        baseMapper.delete(wrapper);

        R returnR = ucenterClient.countRegister(date);
        Integer count = (Integer) returnR.getData().get("count");

        StatisticsDaliy sta = new StatisticsDaliy();
        sta.setRegisterNum(count);
        sta.setDateCalculated(date);
        sta.setVideoViewNum(RandomUtils.nextInt(100, 200));
        sta.setLoginNum(RandomUtils.nextInt(100,200));
        sta.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(sta);

    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaliy> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);
        List<StatisticsDaliy> list = baseMapper.selectList(wrapper);

        //因为返回有两部分数据：日期 和 日期对应数量
        //前端要求数组json结构，对应后端java代码是list集合
        //创建两个list集合，一个日期list，一个数量list
        List<String> date_calculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            StatisticsDaliy daily = list.get(i);
            date_calculatedList.add(daily.getDateCalculated());
            switch (type){
                case "login_num":
                    numDataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //把封装之后两个list集合放到map集合，进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }
}
