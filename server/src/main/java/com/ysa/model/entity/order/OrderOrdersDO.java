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
@Table(name = "t_order_orders")
public class OrderOrdersDO {
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
     * 用户编号
     */
    @Column(name = "member_id")
    private Integer memberId;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 详细地址
     */
    private String addr;

    /**
     * 收件人姓名
     */
    @Column(name = "addr_username")
    private String addrUsername;

    /**
     * 收件人电话
     */
    @Column(name = "addr_phone")
    private String addrPhone;

    /**
     * 应付全款
     */
    private BigDecimal amount;

    /**
     * 订单状态(-2售卖订单超时取消 -1已取消 0待支付 5待搭配 10搭配中 12预览中 15待拣货-未打印拣货单 17待拣货-已打印拣货单 20待发货 25物流中 30已签收 35已预约退回 40回库中 45待质检 50订单异常 55订单完成)
     */
    private Integer status;

    /**
     * 最后支付时间
     */
    @Column(name = "last_pay_time")
    private Date lastPayTime;

    /**
     * 物流单号
     */
    @Column(name = "logistics_no")
    private String logisticsNo;

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