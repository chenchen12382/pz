package com.fh.dao;

import com.fh.dto.FinanceQuery;
import com.fh.model.Finance;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Created by Administrator on 2017/9/15.
 */
public interface FinanceDao {


    PageList<Finance> selectForPage(FinanceQuery query, PageBounds pageBounds);
}
