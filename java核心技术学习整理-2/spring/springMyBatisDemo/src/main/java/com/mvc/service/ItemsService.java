package com.mvc.service;

import com.mvc.po.ItemsCustom;
import com.mvc.po.ItemsQueryVo;

import java.util.List;

public interface ItemsService {

    List<ItemsCustom> findItemList(ItemsQueryVo queryVo) throws Exception;

    ItemsCustom findItemsById(Integer id);

    boolean updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;

    int deleteItems(Integer[] id) throws Exception;

}
