package com.mvc.service.impl;

import com.mvc.mapper.ItemsMapperCustom;
import com.mvc.po.ItemsCustom;
import com.mvc.po.ItemsQueryVo;
import com.mvc.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    public List<ItemsCustom> findItemList(ItemsQueryVo queryVo) throws Exception {
        return itemsMapperCustom.findItemList(queryVo);
    }
}
