package com.fh.model;


/**
 * 报表
 * @author Administrator
 *
 */
public class Report {
	
	private Integer reportfromsId;
	private String name;
	private String mail;
	private Integer phone;
	private String center;
	private String subscribePeople; // 预定人数
	private Integer arrivePeople;  //实际人数
	private Integer orderPeople;   // 下单人数
	private Integer newOrder;   // 新增订单
	private Integer oldOrder;   // 续约订单
	private Integer oneDayMoney;  //一天收入
	private Integer hopeMoney;   //预定收入
	private Integer difference;   //差别
	private String marks;       //备注
	
	
	public Integer getReportfromsId() {
		return reportfromsId;
	}
	public void setReportfromsId(Integer reportfromsId) {
		this.reportfromsId = reportfromsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getSubscribePeople() {
		return subscribePeople;
	}
	public void setSubscribePeople(String subscribePeople) {
		this.subscribePeople = subscribePeople;
	}
	public Integer getArrivePeople() {
		return arrivePeople;
	}
	public void setArrivePeople(Integer arrivePeople) {
		this.arrivePeople = arrivePeople;
	}
	public Integer getOrderPeople() {
		return orderPeople;
	}
	public void setOrderPeople(Integer orderPeople) {
		this.orderPeople = orderPeople;
	}
	public Integer getNewOrder() {
		return newOrder;
	}
	public void setNewOrder(Integer newOrder) {
		this.newOrder = newOrder;
	}
	public Integer getOldOrder() {
		return oldOrder;
	}
	public void setOldOrder(Integer oldOrder) {
		this.oldOrder = oldOrder;
	}
	
	public Integer getHopeMoney() {
		return hopeMoney;
	}
	public void setHopeMoney(Integer hopeMoney) {
		this.hopeMoney = hopeMoney;
	}
	
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public Integer getOneDayMoney() {
		return oneDayMoney;
	}
	public void setOneDayMoney(Integer oneDayMoney) {
		this.oneDayMoney = oneDayMoney;
	}
	public Integer getDifference() {
		return difference;
	}
	public void setDifference(Integer difference) {
		this.difference = difference;
	}
	
}
