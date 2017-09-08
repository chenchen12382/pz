package com.fh.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.base.AssertUtil;
import com.fh.dao.ProgressDao;
import com.fh.dto.ProgressQuery;
import com.fh.model.Progress;
import com.fh.util.CookieUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

@Service
public class ProgressService {
	
	@Autowired
	private ProgressDao progressDao;
	
	
	/**
	 * 查询列表
	 * @param query
	 * @return
	 */
	public Map<String, Object> selectForPage(ProgressQuery query) {
		PageList<Progress> progresses = progressDao.selectForPage(query,query.buildPageBounds());
		Paginator paginator = progresses.getPaginator(); //得到分页对象
		Map<String, Object> result = new HashMap<>();
		result.put("paginator", paginator);
		result.put("rows", progresses);
		result.put("total", paginator.getTotalCount());
		return result;
	}

	/**
	 * 新增
	 * @param progress
	 * @param request 
	 */
	public void insert(Progress progress, HttpServletRequest request) {
		AssertUtil.isNotEmpty(progress.getCenter(), "请选择中心");
		AssertUtil.intIsNotEmpty(progress.getHopeMoney(), "请填写期望薪资");
		AssertUtil.intIsNotEmpty(progress.getFinishMoney(), "请填写达成收入");
		String name = CookieUtil.getCookieValue(request, "realName");
		progress.setCreateMan(name);
		progressDao.insert(progress);
		
	}

	public void update(Progress progress) {
		// TODO Auto-generated method stub
		AssertUtil.isNotEmpty(progress.getCenter(), "请选择中心");
		AssertUtil.intIsNotEmpty(progress.getHopeMoney(), "请填写期望薪资");
		AssertUtil.intIsNotEmpty(progress.getFinishMoney(), "请填写达成收入");
		progressDao.update(progress);
		
		
	}




}
