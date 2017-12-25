package com.fh.service;

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
			if (selectDB.get(0) == null) {
				continue;
			}
			analyzeTotals.addAll(selectDB);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("rows", analyzeTotals);
		return result;
	}
    
	
	/*钉钉推送*/
	public String findAnalyzeTotalToday(AnalyzeTotalQuery query) {	
		// 时间判断	
		List<AnalyzeTotal> analyzeTotals = analyzeTotalDao.selectForPage(query);
		System.out.println(analyzeTotals.size()+"LLLLLLLLLLLLLLLLLL");
		String result = "顾问业绩汇总 \n";
		for (int i = 0; i < analyzeTotals.size(); i++) {
			if (i == analyzeTotals.size() - 1) {
				result+= "\n中心:"+analyzeTotals.get(i).getCenter() + "--总邀约:" + analyzeTotals.get(i).getTotalArriveNum()
						+ "总实到人数:" + analyzeTotals.get(i).getTotalInNum() +  "总下单量:" + analyzeTotals.get(i).getTotalOrderNum() +"\n";
			}
		}
		return result;
	}

	/*private List<AnalyzeTotal> selectForMobilePage(AnalyzeTotalQuery query) {
		List<AnalyzeTotal> result = bulidList(query);
		AnalyzeTotal analyzeTotal = new AnalyzeTotal();	
		String center = null;
		Integer totalPlanNum = 0;// 邀约总人数		
		Integer totalInNum = 0;// 接待总人数
		Integer totalOrderNum = 0;// 下单总人数
		
		for (int i = 0; i < result.size(); i++) {
			totalPlanNum += result.get(i).getTotalPlanNum();// 预约人数			
			totalInNum += result.get(i).getTotalInNum(); // 实到总人数
			totalOrderNum += result.get(i).getTotalOrderNum(); // 下单总人数		
		}
		analyzeTotal.setCenter(center);
		analyzeTotal.setTotalPlanNum(totalPlanNum);
		analyzeTotal.setTotalInNum(totalInNum);
		analyzeTotal.setTotalOrderNum(totalOrderNum);
		result.add(analyzeTotal);
		return result;

	}*/

	private List<AnalyzeTotal> bulidList(AnalyzeTotalQuery query) {
		// 时间判断
		Integer time = query.getTime();
		if (query.getStart() != null && query.getOver() != null) {
			DateUtil.getMinTimeOfDay(query.getStart());
			DateUtil.getMaxTimeOfDay(query.getOver());
		}
		if (query.getStart() != null && query.getOver() == null) {
			query.setOver(new Date());
		}
		if (query.getStart() == null) {
			query.setStart(DateUtil.getFisrtDayOfMonth(new Date()));
			query.setOver(new Date());
		}

		if (time != null) {
			// time = 1;

			if (time == 0) {
				// 当天数据
				query.setStart(DateUtil.getMinTimeOfDay(new Date()));
			} else {
				query.setStart(DateUtil.getFirstDayOfDate(new Date()));
			}
			query.setOver(new Date());
		}

		// 查询中心
		List<String> centers = new ArrayList<>();
		List<AnalyzeTotal> analyzeTotals = new ArrayList<>();
		for (int i = 0; i < centers.size(); i++) {
			query.setCenter(centers.get(i));
			List<AnalyzeTotal> selectForDB = analyzeTotalDao.selectForPage(query);
			if (selectForDB.get(0).getCenter() == null) {
				continue;
			}
			analyzeTotals.addAll(selectForDB);
		}
		return analyzeTotals;
	}
}
