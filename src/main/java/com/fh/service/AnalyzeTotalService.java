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
	public String findCenterTotalToday() {

		AnalyzeTotalQuery query = new AnalyzeTotalQuery();
		query.setTime(0);
		String userName = "dd_message";

		List<AnalyzeTotal> analyzeTotals = selectForMobilePage(query, userName);

		String result = "顾问业绩汇总 \n";
		for (int i = 0; i < analyzeTotals.size(); i++) {
			if (i == analyzeTotals.size() - 1) {
				result += analyzeTotals.get(i).getCenter() + "--总邀约:" + analyzeTotals.get(i).getTotalArriveNum()
						+ "总实到人数:" + analyzeTotals.get(i).getTotalInNum() + "\n";
			} else {
				result += "\n中心:" + analyzeTotals.get(i).getCenter() + " 总成交额:" + analyzeTotals.get(i).getTotalMoney()
						+ " 总下单人数:" + analyzeTotals.get(i).getTotalOrderNum() + "\n";
			}

		}
		return result;
	}

	private List<AnalyzeTotal> selectForMobilePage(AnalyzeTotalQuery query, String userName) {
		List<AnalyzeTotal> result = bulidList(query, userName);
		AnalyzeTotal analyzeTotal = new AnalyzeTotal();
		Integer totalPhoneNum = 0;// 电话总量
		Integer totalPlanNum = 0;// 邀约总人数
		Integer totalArriveNum = 0;// 实到总人数
		Integer totalInNum = 0;// 接待总人数
		Integer totalOrderNum = 0;// 下单总人数
		Integer totalMoney = 0;// 总金
		for (int i = 0; i < result.size(); i++) {
			totalPhoneNum += result.get(i).getTotalPhoneNum();// 总电话量
			totalPlanNum += result.get(i).getTotalPlanNum();// 预约人数
			totalArriveNum += result.get(i).getTotalArriveNum();// 实到人数
			totalInNum += result.get(i).getTotalInNum(); // 实到总人数
			totalOrderNum += result.get(i).getTotalOrderNum(); // 下单总人数
			totalMoney += result.get(i).getTotalMoney(); // 下单总人数

		}
		analyzeTotal.setCenter("合计");
		analyzeTotal.setTotalPhoneNum(totalPhoneNum);
		analyzeTotal.setTotalPlanNum(totalPlanNum);
		analyzeTotal.setTotalInNum(totalInNum);
		analyzeTotal.setTotalOrderNum(totalOrderNum);
		analyzeTotal.setTotalMoney(totalMoney);
		result.add(analyzeTotal);
		return result;

	}

	private List<AnalyzeTotal> bulidList(AnalyzeTotalQuery query, String userName) {
		// TODO Auto-generated method stub
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

		// 查询区域 中心
		List<String> centers = new ArrayList<>();
		String role = "";
		if (!userName.equals("dd_message")) {
			role = userDao.findUserRole(userName);
		} else {
			role = "";
		}
		if (role.equals("中心管理员")) {
			String center = userDao.findUserCenter(userName);
			centers.add(center);
		} else if (role.equals("区域总监") || role.equals("投资人")) {
			centers = analyzeTotalDao.findByPermissionCenter(userName);
		} else {
			centers = analyzeTotalDao.selectAllCenter();
		}
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
