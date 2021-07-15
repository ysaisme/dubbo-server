package com.ysa.service.impl;

import api.ProductService;
import com.alibaba.fastjson.JSON;
import com.ysa.enums.DatePatternEnum;
import com.ysa.model.mapper.order.OrderOrderItemsMapper;
import com.ysa.model.po.CreateOrderPO;
import com.ysa.model.entity.order.OrderOrdersDO;
import com.ysa.model.mapper.order.OrderOrdersMapper;
import com.ysa.service.OrderService;
import com.ysa.util.DataUtil;
import com.ysa.util.DateUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.ProductReducePO;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private OrderOrdersMapper ordersMapper;

    @Autowired
    private OrderOrderItemsMapper itemsMapper;

    @Reference
    private ProductService productService;

    @Override
    public List<OrderOrdersDO> getOrderList() {
        return ordersMapper.selectAll();
    }

    @Override
    @Transactional
    public void createOrder(CreateOrderPO po) {
        log.info("-> {}", JSON.toJSONString(po));
        OrderOrdersDO orderOrdersDO = new OrderOrdersDO();
        BeanUtils.copyProperties(po, orderOrdersDO);
        orderOrdersDO.setRemoveFlag(Boolean.FALSE);
        orderOrdersDO.setCreateAt(1);
        orderOrdersDO.setCreateTime(new Date());
        orderOrdersDO.setProvince("上海市");
        orderOrdersDO.setCity("上海市");
        orderOrdersDO.setDistrict("静安区");
        orderOrdersDO.setAddr("招商局广场");
        orderOrdersDO.setAddrPhone("13838383438");
        orderOrdersDO.setAddrUsername("Ysa");
        orderOrdersDO.setOrderNo(DateUtil.toString(new Date(), DatePatternEnum.PURE_DATETIME_PATTERN) + System.currentTimeMillis());
        orderOrdersDO.setStatus(1);
        orderOrdersDO.setLastPayTime(new Date());
        ordersMapper.insertUseGeneratedKeys(orderOrdersDO);
        if (DataUtil.isNotEmpty(po.getItemsList())) {
            po.getItemsList().forEach(f -> {
                f.setOrderNo(orderOrdersDO.getOrderNo());
                f.setRemoveFlag(Boolean.FALSE);
                f.setStatus(1);
                f.setCreateAt(1);
                f.setCreateTime(new Date());
                if (DataUtil.isEmpty(f.getQuantity()) || f.getQuantity() < 0) {
                    f.setQuantity(1);
                }
            });
            itemsMapper.insertList(po.getItemsList());
//            List<ProductReducePO> list = po.getItemsList().stream()
//                    .filter(f -> DataUtil.isNotEmpty(f.getSpu()))
//                    .map(m -> ProductReducePO.builder()
//                            .spu(m.getSpu())
//                            .qty(m.getQuantity())
//                            .build())
//                    .collect(Collectors.toList());
//            if (DataUtil.isNotEmpty(list)) {
//                productService.reduceQty(list);
//            }
        }
    }

    @Override
    @GlobalTransactional
    public void createOrderTest(CreateOrderPO po) {
        log.info("-> 测试分布式事务 {}", JSON.toJSONString(po));
        createOrder(po);
        List<ProductReducePO> list = po.getItemsList().stream()
                .filter(f -> DataUtil.isNotEmpty(f.getSpu()))
                .map(m -> ProductReducePO.builder()
                        .spu(m.getSpu())
                        .qty(m.getQuantity())
                        .build())
                .collect(Collectors.toList());
        if (DataUtil.isNotEmpty(list)) {
            productService.reduceQty(list);
        }
    }
}
