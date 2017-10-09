package com.fh.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.base.AssertUtil;
import com.fh.dao.TargetDao;
import com.fh.model.Target;

/**
 * Created by Administrator on 2017/10/9.
 */
@Service
public class TargetService {

    @Autowired
    private TargetDao targetDao;

    public  Map<String,Object> selectForPage() {

        List<Target>  targets = targetDao.selectAll();
           Map<String,Object> result = new HashMap<>();
           result.put("rows",targets);
           return  result;

    }

    public void insert(Target target) {
        AssertUtil.isNotEmpty(target.getDistrict(),"请选择区域");
        targetDao.insert(target);

    }

    public void update(Target target) {
        AssertUtil.isNotEmpty(target.getDistrict(),"请选择区域");
        targetDao.update(target);
    }

    public void deleteBatch(String ids) {
        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
        targetDao.deleteBatch(ids);
    }

}
