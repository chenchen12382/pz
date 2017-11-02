package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.base.BaseQuery;
import com.fh.dao.CenterDao;
import com.fh.exception.ParamException;
import com.fh.model.Center;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
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

    public  Map<String,Object> selectForPage(BaseQuery query) {

        PageList<Center>  centers = centerDao.selectAll(query.buildPageBounds());
           Map<String,Object> result = new HashMap<>();
           result.put("rows",centers);
           result.put("total",centers.getPaginator().getTotalCount());
           return  result;



    }

    public void insert(Center center) {
        AssertUtil.isNotEmpty(center.getCenter(),"请输入中心");
        Integer temp = centerDao.findByCenter(center.getCenter());
        if(temp != 0 ){
            throw  new ParamException("您输入的中心已存在！请检查后输入");
        }

//        AssertUtil.intIsNotEmpty(temp,"您输入的中心已存在！请检查后输入！");

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

    public Map<String,Object> selectAll() {
        List<Center> centers=centerDao.selectCenter();
        Map<String,Object> result = new HashMap<>();
        result.put("rows",centers);
        return result;
    }
}
