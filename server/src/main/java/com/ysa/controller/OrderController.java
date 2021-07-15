package com.ysa.controller;

import com.ysa.model.po.CreateOrderPO;
import com.ysa.service.OrderService;
import com.ysa.util.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/14 12:48
 * Modified By:
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/getOrderList")
    public BaseResult getOrderList() {
        return BaseResult.success(orderService.getOrderList());
    }


    @PostMapping("/createOrder")
    public BaseResult createOrder(@RequestBody CreateOrderPO createOrderPO) {
        orderService.createOrder(createOrderPO);
        return BaseResult.success();
    }

    @PostMapping("/createOrderTest")
    public BaseResult createOrderTest(@RequestBody CreateOrderPO createOrderPO) {
        orderService.createOrderTest(createOrderPO);
        return BaseResult.success();
    }
}
