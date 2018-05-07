package com.manager.service;

import com.manager.pojo.mybatisPojo.TbItem;
import com.taotao.common.pojo.EUDataGridResult;

public interface ItemService {
    TbItem getItemById(long itemId);

    EUDataGridResult getItemList(int page, int rows);
}
