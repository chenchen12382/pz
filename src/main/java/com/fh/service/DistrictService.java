package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.base.BaseQuery;
import com.fh.dao.DistrictDao;
import com.fh.model.District;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */
@Service
public class DistrictService {

    @Autowired
    private DistrictDao districtDao;


    public Map<String,Object> selectForPage(BaseQuery query) {
        PageList<District> districts = districtDao.selectForPage(query.buildPageBounds());
        Map<String,Object> result = new HashMap<>();
        result.put("rows",districts);
        result.put("total",districts.getPaginator().getTotalCount());
        return result;

    }

    public void insert(District district) {
        AssertUtil.isNotEmpty(district.getDistrict(),"请填写区域");
        districtDao.insert(district);
    }

    public void update(District district) {
        AssertUtil.isNotEmpty(district.getDistrict(),"请填写区域");
        districtDao.update(district);
    }

    public void deleteBatch(String ids) {
        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
        districtDao.deleteBatch(ids);
    }

    public List<District> findAll() {
        List <District> result = districtDao.findAll();
        return  result;
    }
}
