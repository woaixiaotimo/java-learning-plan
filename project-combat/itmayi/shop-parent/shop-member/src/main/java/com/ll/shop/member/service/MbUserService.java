package com.ll.shop.member.service;

import com.ll.shop.member.dao.MbUserDao;
import com.ll.shop.member.entity.MbUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MbUserService {

    @Autowired
    private MbUserDao mbUserDao;


    public Optional<MbUserEntity> findbyId(Long id) {
        return mbUserDao.findById(id);
    }

}
