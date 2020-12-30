package com.jm.educenter.service;

import com.jm.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jm.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author marc
 * @since 2020-11-26
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    String login(UcenterMember member);
    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDate(String date);
}
