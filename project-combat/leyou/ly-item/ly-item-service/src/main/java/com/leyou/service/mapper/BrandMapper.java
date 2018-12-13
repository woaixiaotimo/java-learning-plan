package com.leyou.service.mapper;

import com.leyou.item.pojo.Brand;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper extends Mapper<Brand>, IdListMapper<Brand, Long> {
}
