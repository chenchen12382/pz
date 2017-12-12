package com.fh.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fh.dao.AnalyzeTotalDao;
import com.fh.dao.UserDao;
import com.fh.dto.AnalyzeTotalQuery;
import com.fh.model.AnalyzeTotal;
import com.fh.util.DateUtil;

@Service
public class AnalyzeTotalService {

    @Autowired
    private AnalyzeTotalDao  analyzeTotalDao;
    @Autowired
    private UserDao userDao;
    public Map<String, Object> selectForPage(AnalyzeTotalQuery query, String userName) {
       List<AnalyzeTotal> analyzeTotal = bulidList(query, userName);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", analyzeTotal);
        return result;
    }

    /**
     * 构建查询讯息
     * @param query
     * @param userName
     * @return
     */
    public List bulidList(AnalyzeTotalQuery query, String userName) {
        //时间判断
        Integer time=query.getTime();
        if(query.getStart()!=null&&query.getOver()!=null){
            DateUtil.getMinTimeOfDay(query.getStart());
            DateUtil.getMaxTimeOfDay(query.getOver());
        }
        if(query.getStart()!=null&&query.getOver()==null){
            query.setOver(new Date());
        }
        if (query.getStart()==null){
            query.setStart(DateUtil.getFisrtDayOfMonth(new Date()));
            query.setOver(new Date());
        }

        if(time==null&&query.getStart()==null) {
            time = 1;
            if (time == 0) {
                //当天数据
                query.setStart(DateUtil.getMinTimeOfDay(new Date()));
            } else {
                query.setStart(DateUtil.getFirstDayOfDate(new Date()));
            }
            query.setOver(new Date());
        }

        //查询区域 中心
        List<String> centers = new ArrayList<>();
        String role = userDao.findUserRole(userName);
        if (role.equals("中心管理员")) {
            String center = userDao.findUserCenter(userName);
            centers.add(center);
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
