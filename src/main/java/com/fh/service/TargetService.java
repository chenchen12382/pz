package com.fh.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fh.exception.ParamException;
import com.fh.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.base.AssertUtil;
import com.fh.dao.TargetDao;
import com.fh.dto.TargetQuery;
import com.fh.model.Target;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

/**
 * Created by Administrator on 2017/10/9.
 */
@Service
public class TargetService {

    @Autowired
    private TargetDao targetDao;
    

 

   public Map<String,Object> selectForPage(TargetQuery query) {
	   PageList<Target> targets = targetDao.selectForPage(query, query.buildPageBounds());
		Paginator paginator = targets.getPaginator(); //得到分页对象
		Map<String, Object> result = new HashMap<>();
		result.put("paginator", paginator);
		result.put("rows", targets);
		result.put("total", paginator.getTotalCount());
		return result;

    }

    public void insert(Target target) {
    	
        AssertUtil.isNotEmpty(target.getDistrict(),"请选择区域");
		String months = target.getMonths();
		if (months.equals("本月")) {
			target.setMonth(new Date());
		} else {
			target.setMonth(DateUtil.getAfterMonth(new Date()));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String sMonth =  sdf.format(target.getMonth());
		Integer selectForMonth =  targetDao.selectForMonth(target.getDistrict(),sMonth);
		if(selectForMonth == 0){
			targetDao.insert(target);
		}else {
			throw new ParamException("當月記錄已存在！請使用修改功能");

		}

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
