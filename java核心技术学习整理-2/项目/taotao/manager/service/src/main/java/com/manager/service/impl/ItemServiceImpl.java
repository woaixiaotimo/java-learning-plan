package com.manager.service.impl;

import com.manager.mapper.mybatisMapper.TbItemMapper;
import com.manager.pojo.mybatisPojo.TbItem;
import com.manager.pojo.mybatisPojo.TbItemExample;
import com.manager.pojo.mybatisPojo.TbItemExample.Criteria;
import com.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.common.pojo.EUDataGridResult;

import java.util.List;


/**
 * 商品管理Service
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p>
 *
 * @author 入云龙
 * @version 1.0
 * @date 2015年9月2日上午10:47:14
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId) {

        //TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //添加查询条件
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    /*
    * 商品列表查询
    * */
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
//        分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        PageInfo pageInfo = new PageInfo(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


}
