package com.mvc.service;

import com.mvc.po.ItemsCustom;
import com.mvc.po.ItemsQueryVo;

import java.util.List;

public interface ItemsService {

    List<ItemsCustom> findItemList(ItemsQueryVo queryVo) throws Exception;
}
