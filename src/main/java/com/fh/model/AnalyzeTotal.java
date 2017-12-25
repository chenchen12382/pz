package com.fh.model;

import com.fh.base.BaseModel;

 /*中
 * 心
 * 顾
 * 问
 * 业
 * 绩
 * 汇
 * 总*/
public class AnalyzeTotal extends BaseModel{
	private String  center;//中心
	private Integer totalPhoneNum;//电话总量
	private Integer totalPlanNum;//邀约总人数
	private Integer totalArriveNum;//实到总人数
	private Integer totalInNum;//接待总人数
	private Integer totalOrderNum;//下单总人数
	private Integer totalMoney;//总金额
	public Integer getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public Integer getTotalPhoneNum() {
		return totalPhoneNum;
	}
	public void setTotalPhoneNum(Integer totalPhoneNum) {
		this.totalPhoneNum = totalPhoneNum;
	}
	public Integer getTotalPlanNum() {
		return totalPlanNum;
	}
	public void setTotalPlanNum(Integer totalPlanNum) {
		this.totalPlanNum = totalPlanNum;
	}
	public Integer getTotalArriveNum() {
		return totalArriveNum;
	}
	public void setTotalArriveNum(Integer totalArriveNum) {
		this.totalArriveNum = totalArriveNum;
	}
	public Integer getTotalInNum() {
		return totalInNum;
	}
	public void setTotalInNum(Integer totalInNum) {
		this.totalInNum = totalInNum;
	}
	public Integer getTotalOrderNum() {
		return totalOrderNum;
	}
	public void setTotalOrderNum(Integer totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}
	

}
