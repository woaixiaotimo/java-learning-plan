package com.manager.service.impl;

import com.common.pojo.EUTreeNode;
import com.manager.mapper.mybatisMapper.TbContentCategoryMapper;
import com.manager.pojo.mybatisPojo.TbContentCategory;
import com.manager.pojo.mybatisPojo.TbContentCategoryExample;
import com.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parentId);

        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            //创建一个节点
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;

    }
}
