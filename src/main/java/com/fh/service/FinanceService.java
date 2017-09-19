package com.fh.service;

import com.fh.dao.FinanceDao;
import com.fh.dto.FinanceQuery;
import com.fh.model.Finance;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/15.
 */
@Service
public class FinanceService {

    @Autowired
    private FinanceDao financeDao;


    public Map<String,Object> selectForPage(FinanceQuery query) {
        PageList<Finance> finances = financeDao.selectForPage(query,query.buildPageBounds());
        Paginator paginator = finances.getPaginator();
        Map<String,Object> result = new HashMap<>();
        result.put("paginator",paginator);
        result.put("rows",finances);
        result.put("total",paginator.getTotalCount());
        return result;

    }
}
