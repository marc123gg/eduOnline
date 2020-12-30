package com.jm.eduorder.service;

import com.jm.eduorder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author marc
 * @since 2020-12-07
 */
public interface TOrderService extends IService<TOrder> {

    String createOrders(String courseId, String memberIdByJwtToken);
}
