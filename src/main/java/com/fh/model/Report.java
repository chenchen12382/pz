package com.fh.model;

import com.fh.base.BaseModel;

/**
 * 报表
 * @author Administrator
 *
 */
public class Report extends BaseModel{

	private String name;  //顾问姓名
	private Integer phone_num; //电话量
	private String center;  // 中心
	private Integer plan_num; // 邀约量
	private Integer arrive_num;  //到访量
	private Integer in_num;   // 接待人数
	private String source;   // 来源
	private Integer order_num;   // 下单人数
	private Integer money;  //收入金额
	private String analysis;   //未报名分析
//	private Integer difference;   //差别
//	private String marks;       //备注


	public String getName() {
		return name;
	}

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

	public void setName(String name) {
		this.name = name;
	}



	public Integer getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(Integer phone_num) {
		this.phone_num = phone_num;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public Integer getPlan_num() {
		return plan_num;
	}

	public void setPlan_num(Integer plan_num) {
		this.plan_num = plan_num;
	}

	public Integer getArrive_num() {
		return arrive_num;
	}

	public void setArrive_num(Integer arrive_num) {
		this.arrive_num = arrive_num;
	}

	public Integer getIn_num() {
		return in_num;
	}

	public void setIn_num(Integer in_num) {
		this.in_num = in_num;
	}


	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	
}
