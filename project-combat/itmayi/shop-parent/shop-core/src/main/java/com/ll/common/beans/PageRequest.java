package com.ll.common.beans;

import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

/**
 * 分页请求参数
 *
 * @author 肖文杰 https://github.com/xwjie/
 */
@Data
public class PageRequest {

    private int page = 1;

    private int pagesize = 10;

    private String sortfield = "";

    private String sort = "";

    private String keyword = "";

    public PageRequest() {
        super();
    }

    public PageRequest(int page, int pagesize, String sortfield, String sort,
                       String keyword) {
        super();
        this.page = page;
        this.pagesize = pagesize;
        this.sortfield = sortfield;
        this.sort = sort;
        this.keyword = keyword;
    }

    public PageRequest getPageable() {
        return new PageRequest(page, pagesize, sortfield, sort, keyword);
    }

    public Pageable toPageable() {
        // pageable里面是从第0页开始的。
        Pageable pageable = null;

        if (StringUtils.isEmpty(sortfield)) {
            pageable = org.springframework.data.domain.PageRequest.of(page - 1, pagesize);
        } else {
            pageable = org.springframework.data.domain.PageRequest.of(page - 1, pagesize,
                    sort.toLowerCase().startsWith("desc") ? Direction.DESC : Direction.ASC,
                    sortfield);
        }

        return pageable;
    }
}
