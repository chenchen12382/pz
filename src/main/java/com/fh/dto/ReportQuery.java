package com.fh.dto;

import com.fh.base.BaseQuery;

public class ReportQuery extends BaseQuery{
	
	private String name;
	private String center;
	private String userName;
	private String userCenter;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCenter() {
		return userCenter;
	}
	public void setUserCenter(String userCenter) {
		this.userCenter = userCenter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	
	
}
