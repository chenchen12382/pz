package com.fh.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.base.AssertUtil;
import com.fh.dao.ReportDao;
import com.fh.dto.ReportQuery;
import com.fh.exception.ParamException;
import com.fh.model.Report;
import com.fh.util.CookieUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

@Service
public class ReportService {

	@Autowired
	private ReportDao reportDao;

	/**
	 * 查询列表
	 * 
	 * @param query
	 * @return
	 */
	public Map<String, Object> selectForPage(ReportQuery query) {
		PageList<Report> reports = reportDao.selectForPage(query, query.buildPageBounds());
		Paginator paginator = reports.getPaginator(); // 得到分页对象
		Map<String, Object> result = new HashMap<>();
		result.put("paginator", paginator);
		result.put("rows", reports);
		result.put("total", paginator.getTotalCount());
		return result;

	}

	public void insert(Report report, HttpServletRequest request) {
		/*AssertUtil.isNotEmpty(report.getName(), "电话量不能为空");
		AssertUtil.intIsNotEmpty(report.getPlan_num(), "邀约人数不能为空");
		AssertUtil.intIsNotEmpty(report.getArrive_num(), "到访量不能为空");
		AssertUtil.intIsNotEmpty(report.getIn_num(), "接收量不能为空");*/
		String name = CookieUtil.getCookieValue(request, "realName");
		report.setName(name);
		reportDao.insert(report);

	}

	/**
	 * 更新字段
	 * @param report
	 */
	public void update(Report report) {
		/*AssertUtil.isNotEmpty(report.getName(), "电话量不能为空");
		AssertUtil.intIsNotEmpty(report.getPlan_num(), "邀约人数不能为空");
		AssertUtil.intIsNotEmpty(report.getArrive_num(), "到访量不能为空");
		AssertUtil.intIsNotEmpty(report.getIn_num(), "接收量不能为空");*/
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
