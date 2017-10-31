package com.fh.model;

import com.fh.base.BaseModel;

/**
 * 报表
 * @author Administrator
 *
 */
public class Report extends BaseModel{

	private String name;  //顾问姓名
	private Integer phoneNum; //电话量
	private String center;  // 中心
	private Integer planNum; // 邀约量
	private Integer arriveNum;  //到访量
	private Integer inNum;   // 接待人数
	private String source;   // 来源
	private Integer orderNum;   // 下单人数
	private Integer money;  //收入金额
	private String analysis;   //未报名分析





	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(Integer phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public Integer getPlanNum() {
		return planNum;
	}

	public void setPlanNum(Integer planNum) {
		this.planNum = planNum;
	}

	public Integer getArriveNum() {
		return arriveNum;
	}

	public void setArriveNum(Integer arriveNum) {
		this.arriveNum = arriveNum;
	}

	public Integer getInNum() {
		return inNum;
	}

	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	
}
