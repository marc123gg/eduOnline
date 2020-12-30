package com.jm.eduorder.service.impl;

import com.jm.commonutils.ordervo.CourseWebVoOrder;
import com.jm.commonutils.ordervo.UcenterMemberOrder;
import com.jm.eduorder.Utils.OrderNoUtil;
import com.jm.eduorder.client.EduClient;
import com.jm.eduorder.client.UcenterClient;
import com.jm.eduorder.entity.TOrder;
import com.jm.eduorder.mapper.TOrderMapper;
import com.jm.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author marc
 * @since 2020-12-07
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        TOrder order = new TOrder();
        //创建Order对象，向order对象里面设置需要数据
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();
    }
}
