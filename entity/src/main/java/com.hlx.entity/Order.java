package com.hlx.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
/*
*
* 订单信息
*
*
* */

@Entity(name = "order_t")
public class Order {
    @Id
    private String orderId;
    //渠道ID
    private String chanId;


    private String productId;

    private String chanUserId;
    //see look \com.hlx.entity\enums\OrderType.java
    private String orderType;
    //see look java\com.hlx.entity\enums\OrderStatus.java
    private String OrderStatus;
    private String outerOrderId;
    private BigDecimal amomunt;
    private String memo;
    private Date createAt;
    private Date updateAt;

    public Order(String orderId, String chanId, String productId, String chanUserId, String orderType, String orderStatus, String outerOrderId, BigDecimal amomunt, String memo, Date createAt, Date updateAt) {
        this.orderId = orderId;
        this.chanId = chanId;
        this.productId = productId;
        this.chanUserId = chanUserId;
        this.orderType = orderType;
        OrderStatus = orderStatus;
        this.outerOrderId = outerOrderId;
        this.amomunt = amomunt;
        this.memo = memo;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", chanId='" + chanId + '\'' +
                ", productId='" + productId + '\'' +
                ", chanUserId='" + chanUserId + '\'' +
                ", orderType='" + orderType + '\'' +
                ", OrderStatus='" + OrderStatus + '\'' +
                ", outerOrderId='" + outerOrderId + '\'' +
                ", amomunt=" + amomunt +
                ", memo='" + memo + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getChanUserId() {
        return chanUserId;
    }

    public void setChanUserId(String chanUserId) {
        this.chanUserId = chanUserId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getOuterOrderId() {
        return outerOrderId;
    }

    public void setOuterOrderId(String outerOrderId) {
        this.outerOrderId = outerOrderId;
    }

    public BigDecimal getAmomunt() {
        return amomunt;
    }

    public void setAmomunt(BigDecimal amomunt) {
        this.amomunt = amomunt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
