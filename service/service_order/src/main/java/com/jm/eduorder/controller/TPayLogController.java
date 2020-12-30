package com.jm.eduorder.controller;


import com.jm.commonutils.R;
import com.jm.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author marc
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class TPayLogController {

    @Autowired
    private TPayLogService payLogService;

    //生成微信支付二维码接口
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
        Map map = payLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    //查询支付状态并更新
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if(map == null){
            return R.fail().msg("支付出错了");
        }
        if(map.get("trade_state").equals("SUCCESS")){
            //支付成功,添加记录到支付表，更新订单表状态
            payLogService.updateOrdersStatus(map);
            return R.ok();
        }
        return R.ok().msg("支付中");
    }
}

