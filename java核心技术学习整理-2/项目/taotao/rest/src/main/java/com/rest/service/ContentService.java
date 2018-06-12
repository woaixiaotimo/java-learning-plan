package com.rest.service;

import com.manager.pojo.mybatisPojo.TbContent;

import java.util.List;

public interface ContentService {
    List<TbContent> getContentList(long contentCid);
}
