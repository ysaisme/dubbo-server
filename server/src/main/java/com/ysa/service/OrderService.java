package com.ysa.service;

import com.ysa.model.po.CreateOrderPO;
import com.ysa.model.entity.order.OrderOrdersDO;

import java.util.List;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/14 12:48
 * Modified By:
 */
public interface OrderService {

    /**
     * 查询订单
     *
     * @return 订单信息
     */
    List<OrderOrdersDO> getOrderList();

    /**
     * 创建订单
     *
     * @param po 订单信息
     */
    void createOrder(CreateOrderPO po);

    /**
     * 测试分布式事务
     *
     * @param po 订单信息
     */
    void createOrderTest(CreateOrderPO po);
}
