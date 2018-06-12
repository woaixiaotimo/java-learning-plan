package com.manager.service;

import com.common.pojo.EUTreeNode;
import com.common.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {

    List<EUTreeNode> getCategoryList(long parentId);

    TaotaoResult insertCatContentCategory(long parentId,String name);
}
