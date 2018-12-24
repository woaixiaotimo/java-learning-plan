package com.imooc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@ApiModel(description = "产品模型")
@Entity
@Table(name = "product", schema = "manager", catalog = "")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private BigDecimal thresholdAmount;
    private BigDecimal stepAmount;
    private Integer lockTerm;
    private BigDecimal rewardRate;

    /**
     * @see com.imooc.entity.enums.ProductStatus
     */
    @ApiModelProperty(value = "状态", dataType = "com.imooc.entity.enums.ProductStatus")
    private String status;
    private String memo;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;
    private String createUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateAt;
    private String updateUser;

    public ProductEntity() {
    }

    public ProductEntity(String id, String name, BigDecimal thresholdAmount, BigDecimal stepAmount, Integer lockTerm, BigDecimal rewardRate, String status) {
        this.id = id;
        this.name = name;
        this.thresholdAmount = thresholdAmount;
        this.stepAmount = stepAmount;
        this.lockTerm = lockTerm;
        this.rewardRate = rewardRate;
        this.status = status;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "threshold_amount")
    public BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    public void setThresholdAmount(BigDecimal thresholdAmount) {
        this.thresholdAmount = thresholdAmount;
    }

    @Basic
    @Column(name = "step_amount")
    public BigDecimal getStepAmount() {
        return stepAmount;
    }

    public void setStepAmount(BigDecimal stepAmount) {
        this.stepAmount = stepAmount;
    }

    @Basic
    @Column(name = "lock_term")
    public Integer getLockTerm() {
        return lockTerm;
    }

    public void setLockTerm(Integer lockTerm) {
        this.lockTerm = lockTerm;
    }

    @Basic
    @Column(name = "reward_rate")
    public BigDecimal getRewardRate() {
        return rewardRate;
    }

    public void setRewardRate(BigDecimal rewardRate) {
        this.rewardRate = rewardRate;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Basic
    @Column(name = "update_user")
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return lockTerm == that.lockTerm &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(thresholdAmount, that.thresholdAmount) &&
                Objects.equals(stepAmount, that.stepAmount) &&
                Objects.equals(rewardRate, that.rewardRate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(memo, that.memo) &&
                Objects.equals(createAt, that.createAt) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(updateAt, that.updateAt) &&
                Objects.equals(updateUser, that.updateUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, thresholdAmount, stepAmount, lockTerm, rewardRate, status, memo, createAt, createUser, updateAt, updateUser);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", thresholdAmount=" + thresholdAmount +
                ", stepAmount=" + stepAmount +
                ", lockTerm=" + lockTerm +
                ", rewardRate=" + rewardRate +
                ", status='" + status + '\'' +
                ", memo='" + memo + '\'' +
                ", createAt=" + createAt +
                ", createUser='" + createUser + '\'' +
                ", updateAt=" + updateAt +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
