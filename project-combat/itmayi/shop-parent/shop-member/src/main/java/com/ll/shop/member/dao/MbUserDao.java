package com.ll.shop.member.dao;

import com.ll.shop.member.entity.MbUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbUserDao extends JpaRepository<MbUserEntity, Long> {
}
