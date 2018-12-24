package com.imooc.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "order_t", schema = "seller", catalog = "")
public class OrderTEntity {
    @Id
    private String orderId;
    private String chanId;
    private String productId;
    private String chanUserId;

    /**
     * @see com.imooc.entity.enums.OrderType
     * */
    private String orderType;

    /**
     * @see com.imooc.entity.enums.OrderStatus
     * */
    private String orderStatus;
    private String outerOrderId;
    private BigDecimal amount;
    private String memo;
    private Date createAt;
    private Date updateAt;

    @Id
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "chan_id")
    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    @Basic
    @Column(name = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "chan_user_id")
    public String getChanUserId() {
        return chanUserId;
    }

    public void setChanUserId(String chanUserId) {
        this.chanUserId = chanUserId;
    }

    @Basic
    @Column(name = "order_type")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Basic
    @Column(name = "order_status")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "outer_order_id")
    public String getOuterOrderId() {
        return outerOrderId;
    }

    public void setOuterOrderId(String outerOrderId) {
        this.outerOrderId = outerOrderId;
    }

    @Basic
    @Column(name = "amount")
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "create_at")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTEntity that = (OrderTEntity) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(chanId, that.chanId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(chanUserId, that.chanUserId) &&
                Objects.equals(orderType, that.orderType) &&
                Objects.equals(orderStatus, that.orderStatus) &&
                Objects.equals(outerOrderId, that.outerOrderId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(memo, that.memo) &&
                Objects.equals(createAt, that.createAt) &&
                Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, chanId, productId, chanUserId, orderType, orderStatus, outerOrderId, amount, memo, createAt, updateAt);
    }
}
