package com.fh.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.ProgressDao;
import com.fh.dto.ProgressQuery;
import com.fh.model.Progress;
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

}
