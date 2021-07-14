package com.ysa.service;

import com.ysa.model.entity.order.OrderOrdersDO;

import java.util.List;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/14 12:48
 * Modified By:
 */
public interface OrderService {

    List<OrderOrdersDO> getOrderList();
}
