package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.dao.CenterDao;
import com.fh.model.Center;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class CenterService {

    @Autowired
    private CenterDao centerDao;

    public  Map<String,Object> selectForPage() {

        List<Center>  centers = centerDao.selectAll();
           Map<String,Object> result = new HashMap<>();
           result.put("rows",centers);
           return  result;



    }

    public void insert(Center center) {
        AssertUtil.isNotEmpty(center.getCenter(),"请选择中心");
        centerDao.insert(center);

    }

    public void update(Center center) {
        AssertUtil.isNotEmpty(center.getCenter(),"请选择中心");
        centerDao.update(center);
    }

    public void deleteBatch(String ids) {
        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
        centerDao.deleteBatch(ids);
    }
}
