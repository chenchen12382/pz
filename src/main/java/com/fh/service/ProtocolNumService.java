package com.fh.service;

import com.fh.base.AssertUtil;
import com.fh.base.BaseQuery;
import com.fh.dao.ProtocolNumDao;
import com.fh.dao.UserDao;
import com.fh.dto.CenterTotalQuery;
import com.fh.dto.ProtocolNumQuery;
import com.fh.exception.ParamException;
import com.fh.model.ProtocolNum;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
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

	@Autowired
	private UserDao userDao;

	public Map<String, Object> selectForPage1(ProtocolNumQuery query) {
		PageList<ProtocolNum> protocolNum = protocolNumDao.selectAll1(query,query.buildPageBounds());
		Map<String, Object> result = new HashMap<>();
		result.put("rows", protocolNum);
		result.put("total", protocolNum.getPaginator().getTotalCount());
		return result;
	}
	
	public Map<String, Object> selectForPage2(ProtocolNumQuery query) {
		PageList<ProtocolNum> protocolNum = protocolNumDao.selectAll2(query,query.buildPageBounds());
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

    public List<ProtocolNum> findAll(String userName, Integer type) {

		Integer centerId=userDao.findUserCenterID(userName);
		List<ProtocolNum> result;
		if(type==1) {
			result = protocolNumDao.selectXybhById(centerId);
		}else if(type==2){
			result = protocolNumDao.selectSjbhById(centerId);
		}else if(type==3){
			result = protocolNumDao.selectSjbhLbsById(centerId);
		}else {
			throw new ParamException("选择失败！联系管理员");
		}
		return result;

	}

	/**
	 * 乐博士协议编号
	 * @param query
	 * @return
	 */
    public Map<String,Object> selectXybhLbs(ProtocolNumQuery query) {
		PageList<ProtocolNum> protocolNum = protocolNumDao.selectXybhLbs(query,query.buildPageBounds());
		Map<String, Object> result = new HashMap<>();
		result.put("rows", protocolNum);
		result.put("total", protocolNum.getPaginator().getTotalCount());
		return result;

    }
}
