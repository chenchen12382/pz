package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.base.BaseQuery;
import com.fh.dao.ProtocolNumDao;
import com.fh.model.ProtocolNum;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/9.
 */
@Service
public class ProtocolNumService {

	@Autowired
	private ProtocolNumDao protocolNumDao;

	public Map<String, Object> selectForPage1(BaseQuery query) {
		PageList<ProtocolNum> protocolNum = protocolNumDao.selectAll1(query.buildPageBounds());
		Map<String, Object> result = new HashMap<>();
		result.put("rows", protocolNum);
		result.put("total", protocolNum.getPaginator().getTotalCount());
		return result;
	}
	
	public Map<String, Object> selectForPage2(BaseQuery query) {
		PageList<ProtocolNum> protocolNum = protocolNumDao.selectAll2(query.buildPageBounds());
		Map<String, Object> result = new HashMap<>();
		result.put("rows", protocolNum);
		result.put("total", protocolNum.getPaginator().getTotalCount());
		return result;
	}

	 public void deleteBatchSjbh(String ids) {
	        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
	        protocolNumDao.deleteBatchSjbh(ids);
	    }
		
	 public void deleteBatchXybh(String ids) {
	        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
	        protocolNumDao.deleteBatchXybh(ids);
	    }
	 
	 
		public Map<String,Object> selectAll1() {
	        List<ProtocolNum> protocolNum=protocolNumDao.selectSjbh();
	        Map<String,Object> result = new HashMap<>();
	        result.put("rows",protocolNum);
	        return result;
	    }
		public Map<String,Object> selectAll2() {
	        List<ProtocolNum> protocolNum=protocolNumDao.selectXybh();
	        Map<String,Object> result = new HashMap<>();
	        result.put("rows",protocolNum);
	        return result;
	    }
}
