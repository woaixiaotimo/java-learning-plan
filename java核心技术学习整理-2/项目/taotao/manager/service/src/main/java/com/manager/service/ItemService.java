package com.manager.service;

import com.common.pojo.TaotaoResult;
import com.manager.pojo.mybatisPojo.TbItem;
import com.common.pojo.EUDataGridResult;

public interface ItemService {
    TbItem getItemById(long itemId);

    EUDataGridResult getItemList(int page, int rows);

    TaotaoResult createItem(TbItem tbItem,String desc) throws Exception;
}
