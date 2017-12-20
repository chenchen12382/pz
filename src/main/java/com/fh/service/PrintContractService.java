package com.fh.service;

import com.fh.base.BaseQuery;
import com.fh.dao.PrintContractDao;
import com.fh.model.PrintContract;
import com.fh.model.Target;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/20.
 */
@Service
public class PrintContractService {

    @Resource
    private PrintContractDao printContractDao;

    /**
     * 新增
     * @param printContract
     */
    public void insert(PrintContract printContract) {
        //条件判断
        //todo
        printContractDao.insert(printContract);

    }

    public Map<String,Object> selectForPage(BaseQuery query) {

        PageList<PrintContract> targets = printContractDao.selectForPage(query, query.buildPageBounds());
        Paginator paginator = targets.getPaginator(); //得到分页对象
        Map<String, Object> result = new HashMap<>();
        result.put("paginator", paginator);
        result.put("rows", targets);
        result.put("total", paginator.getTotalCount());
        return result;
    }
}
