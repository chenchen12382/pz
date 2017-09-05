package com.fh.dto;

import com.fh.base.BaseQuery;

public class ProgressQuery extends BaseQuery{

	private String center;  //中心
	private String centerName;  //中心名称
	private String createMan;   //创建人
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	
	
	
	
}
