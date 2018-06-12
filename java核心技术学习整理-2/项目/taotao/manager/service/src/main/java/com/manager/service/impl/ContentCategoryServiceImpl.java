package com.manager.service.impl;

import com.common.pojo.EUTreeNode;
import com.common.pojo.TaotaoResult;
import com.manager.mapper.mybatisMapper.TbContentCategoryMapper;
import com.manager.pojo.mybatisPojo.TbContentCategory;
import com.manager.pojo.mybatisPojo.TbContentCategoryExample;
import com.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public TaotaoResult insertCatContentCategory(long parentId, String name) {
        TbContentCategory pojo = new TbContentCategory();
        pojo.setName(name);
        pojo.setParentId(parentId);
        pojo.setIsParent(false);
        //状态1-正常，2-删除
        pojo.setStatus(1);
        pojo.setSortOrder(1);
        pojo.setCreated(new Date());
        pojo.setUpdated(new Date());

        tbContentCategoryMapper.insert(pojo);

        //检查并修改父节点的isParent
        TbContentCategory parentCat = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            tbContentCategoryMapper.updateByPrimaryKey(parentCat);
        }

        return TaotaoResult.ok(pojo);
    }
}
