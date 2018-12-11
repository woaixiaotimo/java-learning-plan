package com.ll.common.beans;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import lombok.Data;

/**
 * 分页响应对象
 * 
 * @author 肖文杰 https://github.com/xwjie/
 */
@Data
public class PageResponse<T> {
	private List<T> rows;

	private int page;

	private int pagesize;

	private long total;

	public PageResponse(PageInfo<T> page) {
		this.rows = page.getList();
		this.page = page.getPages();
		this.pagesize = page.getSize();
		this.total = page.getTotal();
	}

}
