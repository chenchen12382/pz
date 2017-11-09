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
	
/*
	public void insert1(ProtocolNum protocolNum) {
		AssertUtil.isNotEmpty(protocolNum.getXybh(), "请输入收据编号");
		String temp = protocolNumDao.findBySjbh(protocolNum.getSjbh());
		AssertUtil.isNotEmpty(temp, "您输入的收据编号已存在！请检查后输入！");
		protocolNumDao.insert1(protocolNum);
	}
	public void insert2(ProtocolNum protocolNum) {
		AssertUtil.isNotEmpty(protocolNum.getXybh(), "请输入协议编号");
		String temp = protocolNumDao.findByXybh(protocolNum.getXybh());
		AssertUtil.isNotEmpty(temp, "您输入的协议编号已存在！请检查后输入！");
		protocolNumDao.insert2(protocolNum);
	}*/	
	/*
	public void update1(ProtocolNum protocolNum) {
		// AssertUtil.isNotEmpty(protocolNum.getSjbh(),"请选择数据编号");
		protocolNumDao.update1(protocolNum);	
	}
	public void update2(ProtocolNum protocolNum) {
			// AssertUtil.isNotEmpty(protocolNum.getSjbh(),"请选择数据编号");
			protocolNumDao.update2(protocolNum);
	}
		*/
	
	 public void deleteBatch1(String ids) {
	        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
	        protocolNumDao.deleteBatch1(ids);
	    }
		
	 public void deleteBatch2(String ids) {
	        AssertUtil.isNotEmpty(ids,"请选择记录进行删除");
	        protocolNumDao.deleteBatch2(ids);
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
