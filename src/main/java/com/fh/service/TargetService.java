package com.fh.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

   public  Map<String,Object> selectForPage(TargetQuery query) {
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
		System.out.println(months);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
		Date date = new Date();
		String T1 = dateFormat.format(date);
		Date time1 = null;
		try {
			time1 = dateFormat.parse(T1);//本月
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 设置为当前时间
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1); // 设置为下一个月
		date = calendar.getTime();
		String T2 = dateFormat.format(date);
		Date time2 = null;
		try {
			time2 = dateFormat.parse(T2);//次月
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (months.equals("本月")) {
			target.setMonth(time1);
		} else {
			target.setMonth(time2);
		}
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
