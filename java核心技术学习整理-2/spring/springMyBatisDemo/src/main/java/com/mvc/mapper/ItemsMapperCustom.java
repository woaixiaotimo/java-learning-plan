package com.mvc.mapper;

import com.mvc.po.ItemsCustom;
import com.mvc.po.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustom {

    //商品查询列表
    List<ItemsCustom> findItemList(ItemsQueryVo itemsQueryVo) throws Exception;
}
