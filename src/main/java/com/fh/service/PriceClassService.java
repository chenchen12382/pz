package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.dao.PriceClassDao;
import com.fh.model.PriceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service
public class PriceClassService {

    @Autowired
    private PriceClassDao priceClassDao;

    public Map<String,Object> findAll() {
        List<PriceClass> priceClasses = priceClassDao.findAll();
        Map<String,Object> result = new HashMap<>();
        result.put("rows",priceClasses);
        return result;
    }

    public void insert(PriceClass priceClass) {
        AssertUtil.isNotEmpty(priceClass.getSaleClass(),"请输入课程");
        AssertUtil.intIsNotEmpty(priceClass.getPrice(),"请输入课程价格");
        priceClassDao.insert(priceClass);

    }

    public void update(PriceClass priceClass) {
        AssertUtil.isNotEmpty(priceClass.getSaleClass(),"请输入课程");
        AssertUtil.intIsNotEmpty(priceClass.getPrice(),"请输入课程价格");
        priceClassDao.update(priceClass);

    }

    public void deleteBatch(String ids) {
        AssertUtil.isNotEmpty(ids,"请选择记录");
        priceClassDao.deleteBatch(ids);

    }
}
