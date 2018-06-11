package com.manager.service;

import com.common.pojo.EUTreeNode;

import java.util.List;

public interface ContentCategoryService {

    List<EUTreeNode> getCategoryList(long parentId);
}
