package com.fh.model;

import java.sql.Date;

import com.fh.base.BaseModel;

/**
 * 进度
 * @author Administrator
 *
 */
public class Progress extends BaseModel{
	
	
	private String createMan;   //创建人
	private String center;      //中心
//	private String centerName;  //中心名
	private String phone;       //电话
//	private Integer hopeMoney;  //预计收入
	private Integer hopeMoney;
	private Integer finishMoney;  //达成收入
	private Integer unfinishMoney;  //未达成收入
	private String marks;         //备注
	
	
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getFinishMoney() {
		return finishMoney;
	}
	public void setFinishMoney(Integer finishMoney) {
		this.finishMoney = finishMoney;
	}
	public Integer getUnfinishMoney() {
		return unfinishMoney;
	}
	public void setUnfinishMoney(Integer unfinishMoney) {
		this.unfinishMoney = unfinishMoney;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public Integer getHopeMoney() {
		return hopeMoney;
	}
	public void setHopeMoney(Integer hopeMoney) {
		this.hopeMoney = hopeMoney;
	}
	

	
	
}
