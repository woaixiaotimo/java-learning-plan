package com.mvc.service.impl;

import com.mvc.mapper.ItemsMapper;
import com.mvc.mapper.ItemsMapperCustom;
import com.mvc.po.Items;
import com.mvc.po.ItemsCustom;
import com.mvc.po.ItemsQueryVo;
import com.mvc.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;
    @Autowired
    private ItemsMapper itemsMapper;

    public List<ItemsCustom> findItemList(ItemsQueryVo queryVo) throws Exception {
        return itemsMapperCustom.findItemList(queryVo);
    }

    public ItemsCustom findItemsById(Integer id) {
        Items items = itemsMapper.selectByPrimaryKey(id);
        //todo - 业务处理
        ItemsCustom itemsCustom = new ItemsCustom();
        //将属性拷贝到新对象
        BeanUtils.copyProperties(items, itemsCustom);
        return itemsCustom;
    }

    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //todo - 校验传入数据

        //更新商品信息 - 使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段包括大文本信息
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);

    }
}
