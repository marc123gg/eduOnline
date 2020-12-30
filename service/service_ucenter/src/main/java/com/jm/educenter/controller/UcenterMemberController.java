package com.jm.educenter.controller;


import com.jm.commonutils.JwtUtils;
import com.jm.commonutils.R;
import com.jm.commonutils.ordervo.UcenterMemberOrder;
import com.jm.educenter.entity.UcenterMember;
import com.jm.educenter.entity.vo.RegisterVo;
import com.jm.educenter.service.UcenterMemberService;
import com.jm.servicebase.exceptionhandler.JmDiyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    public Map loginUser(@RequestBody UcenterMember member){
        String token = memberService.login(member);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("flag", true);
        map.put("msg", "success");
        return map;
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        System.out.println("ddddddddddddddddddddddd" + request.getHeader("token"));
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        //根据用户id获取用户信息
        UcenterMember member = memberService.getById(userId);
        return R.ok().data("userInfo", member);
    }

    //根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder memberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member, memberOrder);
        return memberOrder;
    }

    //查询某一天的注册人数
    @GetMapping("countRegister/{date}")
    public R countRegister(@PathVariable String date){
        int count = memberService.countRegisterDate(date);
        return R.ok().data("count", count);
    }

}

