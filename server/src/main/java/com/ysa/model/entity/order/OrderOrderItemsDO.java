package com.ysa.model.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_order_order_items")
public class OrderOrderItemsDO {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * spu
     */
    private String spu;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 会员价
     */
    private BigDecimal price;

    /**
     * 建议零售价
     */
    @Column(name = "price_suggested")
    private BigDecimal priceSuggested;

    /**
     * 实际价格
     */
    @Column(name = "price_real")
    private BigDecimal priceReal;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 状态(0:初始,1:购买，2:退款)
     */
    private Integer status;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_at")
    private Integer createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 最后更新人
     */
    @Column(name = "update_at")
    private Integer updateAt;

    /**
     * 是否删除
     */
    @Column(name = "remove_flag")
    private Boolean removeFlag;

    /**
     * 备注
     */
    private String remark;
}