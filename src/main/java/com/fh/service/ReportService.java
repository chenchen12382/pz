package com.fh.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.base.AssertUtil;
import com.fh.dao.CenterDao;
import com.fh.dao.ReportDao;
import com.fh.dao.UserDao;
import com.fh.dto.ReportQuery;
import com.fh.exception.ParamException;
import com.fh.model.Report;
import com.fh.model.User;
import com.fh.util.CookieUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

@Service
public class ReportService {

	@Autowired
	private ReportDao reportDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CenterDao centerDao;

	/**
	 * 查询列表
	 * @param query
	 * @return
	 */
    public Map<String, Object> selectForPage(ReportQuery query) {
    	PageList<Report> reports = reportDao.selectForPage(query,query.buildPageBounds());
    	Paginator paginator =reports.getPaginator();
    	// 得到分页对象 
    	Map<String, Object> result = new HashMap<>(); 
    	result.put("paginator", paginator);
    	result.put("rows", reports);
    	result.put("total", paginator.getTotalCount()); 
		 return result;	  
	  }
		
   //各中心报表
	public Map<String, Object> selectCenterList(ReportQuery query, HttpServletRequest request) {
		// 判断是哪个中心
		String userName = CookieUtil.getCookieValue(request, "userName");
		// 管理员
		String userRole = userDao.findUserRole(userName);
		// 查询中心
		User user = userDao.findByUserName(userName);
		AssertUtil.notNull(user, "请关闭浏览器重试！");
		String center = user.getCenter();
		query.setUserCenter(center);
//		System.out.println(query.getCenter()+"*********************************");
		if (userRole.equals("系统管理员")) {
			PageList<Report> reports = reportDao.selectForPage(query, query.buildPageBounds());
			Paginator paginator = reports.getPaginator();
			Map<String, Object> result = new HashMap<>();
			result.put("paginator", paginator);
			result.put("rows", reports);
			result.put("total", paginator.getTotalCount());
			return result;
		}

		PageList<Report> reports = reportDao.selectCenterList(query, query.buildPageBounds());
		Paginator paginator = reports.getPaginator();
		Map<String, Object> result = new HashMap<>();
		result.put("paginator", paginator);
		result.put("rows", reports);
		result.put("total", paginator.getTotalCount());
		return result;
	}


	public void insert(Report report, HttpServletRequest request) {
		 //判断是哪个中心
        String userName = CookieUtil.getCookieValue(request,"userName");
        //查询中心
        User user=userDao.findByUserName(userName);
        AssertUtil.notNull(user,"系统出错，请联系管理员");
        String userCenter = user.getCenter();
        report.setCenter(userCenter);
        System.out.println(userCenter+userName);
		reportDao.insert(report);
	}

	/**
	 * 更新字段
	 * 
	 * @param report
	 */
	public void update(Report report) {

		reportDao.update(report);

	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 */
	public void deleteBatch(String ids) {
		if (StringUtils.isBlank(ids)) {
			throw new ParamException("请选择记录进行删除");
		}
		reportDao.deleteBatch(ids);

	}

}
