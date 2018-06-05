package com.manager.service.impl;

import com.common.pojo.TaotaoResult;
import com.common.utils.IDUtils;
import com.manager.mapper.mybatisMapper.TbItemDescMapper;
import com.manager.mapper.mybatisMapper.TbItemMapper;
import com.manager.mapper.mybatisMapper.TbItemParamMapper;
import com.manager.pojo.mybatisPojo.TbItem;
import com.manager.pojo.mybatisPojo.TbItemDesc;
import com.manager.pojo.mybatisPojo.TbItemExample;
import com.manager.pojo.mybatisPojo.TbItemExample.Criteria;
import com.manager.pojo.mybatisPojo.TbItemParam;
import com.manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.common.pojo.EUDataGridResult;

import java.util.Date;
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


    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamMapper tbItemParamMapper;


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


    @Override
    public TaotaoResult createItem(TbItem item, String desc, String param) throws Exception {
        //item补全
        //生成商品ID
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //插入到数据库
        itemMapper.insert(item);
        TaotaoResult taotaoResult = insertItemsDesc(itemId, desc);

        if (taotaoResult.getStatus() != 200) {
            throw new Exception();
        }

        TaotaoResult taotaoResult2 = insertItemsParam(itemId, param);

        if (taotaoResult2.getStatus() != 200) {
            throw new Exception();
        }

        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemsParam(Long itemId, String param) {
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setId(itemId);
        tbItemParam.setParamData(param);
        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }

    //添加商品描述
    private TaotaoResult insertItemsDesc(long itemId, String desc) {
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();
    }


}
