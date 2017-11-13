package com.fh.controller;
import com.fh.base.BaseController;
import com.fh.base.BaseQuery;
import com.fh.base.ResultInfo;
import com.fh.service.ProtocolNumService;
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
                return "xybh";
            case 2 :
                return "sjbh";
        }
    }
    
    /*收据编号*/
    @RequestMapping("listSjbh")
	@ResponseBody
	public Map<String, Object> selectForPage1(BaseQuery query) {
    	
		Map<String, Object> result = protocolNumService.selectForPage1(query);
		return result;
	}

	@RequestMapping("deleteSjbh")
	@ResponseBody
	public ResultInfo deleteBatchSjbh(String ids) {
		protocolNumService.deleteBatchSjbh(ids);
		return success("删除成功");
	}

	/*协议编号*/
	  @RequestMapping("listXybh")
		@ResponseBody
		public Map<String, Object> selectForPage2(BaseQuery query) {
			Map<String, Object> result = protocolNumService.selectForPage2(query);
			return result;
		}

		@RequestMapping("deleteXybh")
		@ResponseBody
		public ResultInfo deleteBatch2(String ids) {
			protocolNumService.deleteBatchXybh(ids);
			return success("删除成功");
		}

	

}
