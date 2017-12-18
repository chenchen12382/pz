package com.fh.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.dao.AnalyzeTotalDao;
import com.fh.dto.AnalyzeTotalQuery;
import com.fh.model.AnalyzeTotal;
import com.fh.util.DateUtil;
import java.util.*;

@Service
public class AnalyzeTotalService {

	@Autowired
	private AnalyzeTotalDao analyzeTotalDao;

	public Map<String, Object> selectForPage(AnalyzeTotalQuery query) {
        //时间判断
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
		//	System.out.println(selectDB.size()+"ppppppppp"+selectDB+"hhhhhhhhhhhhh");
			if (selectDB.get(0) == null) {
				continue;
			}
			analyzeTotals.addAll(selectDB);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("rows", analyzeTotals);
		return result;
	}

}
