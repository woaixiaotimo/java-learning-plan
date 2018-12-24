package com.imooc.manager.repositories;

import com.imooc.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: 啊Q
 * @Date: 2018-12-22 16:24
 * @Description:
 */

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    Page<ProductEntity> findAll(Specification<ProductEntity> specification, Pageable pageable);
}