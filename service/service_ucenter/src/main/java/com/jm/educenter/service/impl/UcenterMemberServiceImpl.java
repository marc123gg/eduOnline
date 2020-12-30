package com.jm.educenter.service.impl;

import com.alibaba.nacos.common.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jm.commonutils.JwtUtils;
import com.jm.educenter.entity.UcenterMember;
import com.jm.educenter.entity.vo.RegisterVo;
import com.jm.educenter.mapper.UcenterMemberMapper;
import com.jm.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jm.commonutils.MD5;
import com.jm.servicebase.exceptionhandler.JmDiyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author marc
 * @since 2020-11-26
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String > redisTemplate;

    @Override
    public String login(UcenterMember member) throws JmDiyException {
        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new JmDiyException(20001, "登录失败");
        }

        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);

        //判断用户实体是否为空
        if(mobileMember == null){
            throw new JmDiyException(20001, "登录失败");
        }

        //md5加密密码对比
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new JmDiyException(20001, "登录失败");
        }

        //是否禁用
        if(mobileMember.getIsDisabled()){
            throw new JmDiyException(20001, "登录失败");
        }
        //返回token令牌
        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());


    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        System.out.println(code + mobile + nickname + password);
        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)){
            throw new JmDiyException(20001, "须填写所有字段");
        }



        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(StringUtils.isEmpty(redisCode)){
            throw new JmDiyException(20001, "验证码过期");
        }

        if(!code.equals(redisCode)){
            throw new JmDiyException(20001, "验证码错误");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0){
            throw new JmDiyException(20001, "该手机号已注册");
        }

        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setIsDisabled(false);
        member.setAvatar("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606457630131&di=2ae88551cb9d5bb53545b72091810ad5&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201801%2F26%2F20180126230814_uUSAk.thumb.700_0.jpeg");
        member.setPassword(MD5.encrypt(password));
        baseMapper.insert(member);

    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer countRegisterDate(String date) {
        return 0;
    }
}
