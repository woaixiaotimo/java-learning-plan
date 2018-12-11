package com.ll.shop.member.controller;

import com.ll.shop.member.entity.MbUserEntity;
import com.ll.shop.member.service.MbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MbUserService mbUserService;

    @RequestMapping("")
    public Optional<MbUserEntity> findById(Long id) {
        return mbUserService.findbyId(id);
    }

}
