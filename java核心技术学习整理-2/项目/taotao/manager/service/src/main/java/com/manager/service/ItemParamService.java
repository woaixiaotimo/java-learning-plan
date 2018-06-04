package com.manager.service;

import com.common.pojo.TaotaoResult;
import com.manager.pojo.mybatisPojo.TbItemParam;

public interface ItemParamService {

    TaotaoResult getItemParamByCid(long cid);


    TaotaoResult insertItemParam(TbItemParam itemParam);
}
