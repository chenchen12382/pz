package com.fh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.base.AssertUtil;
import com.fh.base.BaseQuery;
import com.fh.dao.UpLogDao;
import com.fh.model.UpLog;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * Created by Administrator on 2017/10/25.
 */
@Service
public class UpLogService {

    @Autowired
    private UpLogDao upLogDao;

    public  Map<String,Object> selectForPage(BaseQuery query) {

        PageList<UpLog>  upLogs = upLogDao.selectAll(query.buildPageBounds());
           Map<String,Object> result = new HashMap<>();
           result.put("rows",upLogs);
           result.put("total",upLogs.getPaginator().getTotalCount());
           return  result;
    }

    public void insert(UpLog upLog) {
        AssertUtil.isNotEmpty(upLog.getTitle(),"请输入标题");
        String temp = upLogDao.findByTitle(upLog.getTitle());
       // AssertUtil.isNotEmpty(temp,"您输入的标题已存在！请检查后输入！");
        upLogDao.insert(upLog);

    }

    public void update(UpLog upLog) {
        AssertUtil.isNotEmpty(upLog.getTitle(),"请选择标题");
        upLogDao.update(upLog);
    }

    public void deleteBatch(String ids) {
        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
        upLogDao.deleteBatch(ids);
    }

    public Map<String,Object> selectAll() {
        List<UpLog> upLogs=upLogDao.selectUpLog();
        Map<String,Object> result = new HashMap<>();
        result.put("rows",upLogs);
        return result;
    }
}
