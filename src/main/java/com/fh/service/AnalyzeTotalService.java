package com.fh.service;

import com.fh.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.dao.AnalyzeTotalDao;
import com.fh.dao.UserDao;
import com.fh.dto.AnalyzeTotalQuery;
import com.fh.model.AnalyzeTotal;
import com.fh.util.DateUtil;
import java.util.*;

@Service
public class AnalyzeTotalService {

	@Autowired
	private AnalyzeTotalDao analyzeTotalDao;
	@Autowired
	private UserDao userDao;

	public Map<String, Object> selectForPage(AnalyzeTotalQuery query) {
		// 时间判断
		if (query.getStart() == null) {
			query.setStart(DateUtil.getFirstDayOfDate(new Date()));
			query.setOver(DateUtil.getLastDayOfDate(new Date()));
		}
		if (query.getStart() != null && query.getOver() == null) {
			query.setOver(new Date());
		}
		// 查询 中心
		List<AnalyzeTotal> analyzeTotals = new ArrayList<>();
		List<String> centers = new ArrayList<>();
		centers = analyzeTotalDao.selectAllCenter();
		for (int i = 0; i < centers.size(); i++) {
			query.setCenter(centers.get(i));
			List<AnalyzeTotal> selectDB = analyzeTotalDao.selectForPage(query);
			if (selectDB.get(0)== null) {
				continue;
			}
			analyzeTotals.addAll(selectDB);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("rows", analyzeTotals);
		return result;
	}
    
	
	/*钉钉推送*/
	public String findAnalyzeTotalToday() {
		AnalyzeTotalQuery query = new AnalyzeTotalQuery();
		query.setStart(DateUtil.getMinTimeOfDay(new Date()));
		query.setOver(DateUtil.getMaxTimeOfDay(new Date()));

		List<Report> analyzeTotals =analyzeTotalDao.selectCenterToDD(query);
		
		  String result = "各中心顾问业绩统计: \n";
		for (int i =0 ;i<analyzeTotals.size();i++){

			result+="中心:"+analyzeTotals.get(i).getCenter()+" 邀约量:"+analyzeTotals.get(i).getPlanNum()+" 到访人数:"+analyzeTotals.get(i).getArriveNum()+
					" 下单人数:"+analyzeTotals.get(i).getOrderNum()+"\n";
		}
		return result;
	}
}
