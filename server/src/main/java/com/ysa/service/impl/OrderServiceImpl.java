package com.ysa.service.impl;

import com.ysa.model.entity.order.OrderOrdersDO;
import com.ysa.model.mapper.order.OrderOrdersMapper;
import com.ysa.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/14 12:48
 * Modified By:
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderOrdersMapper orderOrdersMapper;

    @Override
    public List<OrderOrdersDO> getOrderList() {
        return orderOrdersMapper.selectAll();
    }
}
