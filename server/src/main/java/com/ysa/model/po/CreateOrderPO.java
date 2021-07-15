package com.ysa.model.po;

import com.ysa.model.entity.order.OrderOrderItemsDO;
import com.ysa.model.entity.order.OrderOrdersDO;
import lombok.Data;

import java.util.List;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/14 14:15
 * Modified By:
 */
@Data
public class CreateOrderPO extends OrderOrdersDO {

    /**
     * 订单详情
     */
    private List<OrderOrderItemsDO> itemsList;
}
