package com.jm.msmservice.controller;

import com.jm.commonutils.R;
import com.jm.msmservice.Utils.RandomUtil;
import com.jm.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/edumsm/msm")
@RestController
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //先尝试从redis中获取验证码
        String exitCode = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(exitCode)){
            return R.ok();
        }
        //生成随机值
        String code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        //调用service发送短信
        boolean isSend = msmService.send(param, phone);
        if(isSend){
            //设置有效时长
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        }
        return R.fail().msg("短信发送失败");
    }

}
