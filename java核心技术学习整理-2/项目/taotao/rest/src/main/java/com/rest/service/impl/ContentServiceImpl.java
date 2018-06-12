package com.rest.service.impl;

import com.manager.mapper.mybatisMapper.TbContentMapper;
import com.manager.pojo.mybatisPojo.TbContent;
import com.manager.pojo.mybatisPojo.TbContentExample;
import com.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> getContentList(long contentCid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        return  tbContentMapper.selectByExample(example);
    }
}
