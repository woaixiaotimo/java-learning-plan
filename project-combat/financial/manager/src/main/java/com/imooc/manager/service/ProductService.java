package com.imooc.manager.service;

import com.imooc.entity.ProductEntity;
import com.imooc.entity.enums.ProductStatus;
import com.imooc.manager.error.ErrorEnum;
import com.imooc.manager.repositories.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 产品服务类
 *
 * @Auther: 啊Q
 * @Date: 2018-12-23 09:15
 * @Description:
 */
@Service
public class ProductService {

    private Logger log = LoggerFactory.getLogger(ProductService.class);


    @Autowired
    private ProductRepository productRepository;

    public ProductEntity addProduct(ProductEntity productEntity) {

        log.debug("创建产品，参数{}", productEntity);

        //数据校验
        checkProduct(productEntity);

        //创建默认值
        setDefault(productEntity);

        //添加产品
        ProductEntity result = productRepository.save(productEntity);

        log.debug("创建产品，结果{}", result);

        return result;
    }


    /**
     * 查询单个产品
     */
    public ProductEntity findOne(String id) {
        Assert.notNull(id, "需要产品编号参数");
        log.debug("查询单个产品，id={}", id);


        //查询单个产品
        ProductEntity product = new ProductEntity();
        product.setId(id);
        Example<ProductEntity> example = Example.of(product);
        Optional<ProductEntity> result = productRepository.findOne(example);

        log.debug("查询单个产品，结果={}", id);
        return result.get();
    }

    /**
     * 分页查询产品
     */
    public Page<ProductEntity> quary(List<String> idList,
                                     BigDecimal minRewardRate, BigDecimal maxRewardRate,
                                     List<String> statusList,
                                     Pageable pageable) {

        log.debug("查询产品，idList={},   minRewardRate={},  maxRewardRate={},statusList={},pageable={}",
                idList, minRewardRate, maxRewardRate, statusList, pageable);
        Page<ProductEntity> page = productRepository
                .findAll((Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
                    Expression<String> idCol = root.get("id");
                    Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                    Expression<String> statuCol = root.get("status");
                    List<Predicate> predicates = new ArrayList<Predicate>();

                    if (idList != null && idList.size() > 0) {
                        predicates.add(idCol.in(idList));
                    }
                    if (minRewardRate != null && BigDecimal.ZERO.compareTo(minRewardRate) <= 0) {
                        predicates.add(criteriaBuilder.ge(rewardRateCol, minRewardRate));
                    }

                    if (maxRewardRate != null && BigDecimal.ZERO.compareTo(maxRewardRate) <= 0) {
                        predicates.add(criteriaBuilder.le(rewardRateCol, maxRewardRate));
                    }

                    if (statusList != null && statusList.size() >= 0) {
                        predicates.add(statuCol.in(statusList));
                    }
                    return query.where(predicates.toArray(new Predicate[0])).getRestriction();
                }, pageable);
        log.debug("分页查询产品，结果={}", page);
        return page;

    }

    /**
     * 1.非空数据
     * 2.收益率在0 - 30%以内
     * 3.投资步长需为整数
     */
    private void checkProduct(ProductEntity productEntity) {

        //非空校验
        Assert.notNull(productEntity.getId(), ErrorEnum.ID_NOT_NULL.getCode());
        Assert.notNull(productEntity.getName(), "名称不可为空！");
        Assert.notNull(productEntity.getThresholdAmount(), "起投金额不可为空！");
        Assert.notNull(productEntity.getStepAmount(), "投资步长！");
        Assert.notNull(productEntity.getLockTerm(), "锁定期不可为空！");
        Assert.notNull(productEntity.getRewardRate(), "收益率不可为空！");
        Assert.notNull(productEntity.getLockTerm(), "状态不可为空！");

        Assert.isTrue(BigDecimal.ZERO.compareTo(productEntity.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(productEntity.getRewardRate()) >= 0, "收益率范围错误");
        Assert.isTrue(BigDecimal.valueOf(productEntity.getStepAmount().longValue()).compareTo(productEntity.getStepAmount()) == 0, "投资步长为整数");
    }

    /**
     * 设置默认值
     * 创建时间、更新时间
     * 投资步长、锁定期、状态
     */
    public void setDefault(ProductEntity productEntity) {
        if (productEntity.getCreateAt() == null) {
            productEntity.setCreateAt(new Date());
        }

        if (productEntity.getUpdateAt() == null) {
            productEntity.setUpdateAt(new Date());
        }

        if (productEntity.getStepAmount() == null) {
            productEntity.setStepAmount(BigDecimal.ZERO);
        }

        if (productEntity.getLockTerm() == null) {
            productEntity.setLockTerm(0);
        }

        if (productEntity.getStatus() == null) {
            productEntity.setStatus(ProductStatus.AUDITING.name());
        }

    }
}
