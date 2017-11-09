package com.fh.controller;
import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.model.ProtocolNum;
import com.fh.service.ProtocolNumService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/11/9.
 */
@Controller
@RequestMapping("protocol")
public class ProtocolNumController extends BaseController{

    @Autowired
    private ProtocolNumService protocolNumService;


    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type){
        switch (type){
            default:
                return "sjbh";
            case 2 :
                return "xybh";
        }
    }
    
    /*收据编号*/
    @RequestMapping("list1")
	@ResponseBody
	public Map<String, Object> selectForPage1(BaseQuery query) {
		Map<String, Object> result = protocolNumService.selectForPage1(query);
		return result;
	}

	@RequestMapping("find_all1")
	@ResponseBody
	public List<ProtocolNum> findAll1() {
		Map<String, Object> result = protocolNumService.selectAll1();
		return (List<ProtocolNum>) result.get("rows");

	}
	
	@RequestMapping("delete1")
	@ResponseBody
	public ResultInfo deleteBatch(String ids) {
		protocolNumService.deleteBatch1(ids);
		return success("删除成功");
	}
/*
	@RequestMapping("add1")
	@ResponseBody
	public ResultInfo insert1(ProtocolNum protocolNum) {
		protocolNumService.insert1(protocolNum);
		return success("添加成功");
	}

	@RequestMapping("update1")
	@ResponseBody
	public ResultInfo update1(ProtocolNum protocolNum) {
		protocolNumService.update1(protocolNum);
		return success("修改成功");
	}
*/
	
	/*协议编号*/
	  @RequestMapping("list2")
		@ResponseBody
		public Map<String, Object> selectForPage2(BaseQuery query) {
			Map<String, Object> result = protocolNumService.selectForPage2(query);
			return result;
		}

		@RequestMapping("find_all2")
		@ResponseBody
		public List<ProtocolNum> findAll2() {
			Map<String, Object> result = protocolNumService.selectAll2();
			return (List<ProtocolNum>) result.get("rows");
		}
		
		@RequestMapping("delete2")
		@ResponseBody
		public ResultInfo deleteBatch2(String ids) {
			protocolNumService.deleteBatch2(ids);
			return success("删除成功");
		}

	/*	@RequestMapping("add2")
		@ResponseBody
		public ResultInfo insert2(ProtocolNum protocolNum) {
			protocolNumService.insert2(protocolNum);
			return success("添加成功");
		}

		@RequestMapping("update2")
		@ResponseBody
		public ResultInfo update2(ProtocolNum protocolNum) {
			protocolNumService.update2(protocolNum);
			return success("修改成功");
		}*/

}
