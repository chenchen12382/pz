package com.fh.model;

import java.sql.Date;

import com.fh.base.BaseModel;

/**
 * 进度
 * @author Administrator
 *
 */
public class Progress extends BaseModel{
	
	private Integer progressId;  //进度ID
	private Integer createman;   //创建人
	private String center;      //中心
	private String centerName;  //中心名
	private String phone;       //电话
	private Integer hopeMoney;  //预计收入
	private Integer finishMoney;  //达成收入
	private Integer unfinishMoney;  //未达成收入
	private String marks;         //备注
	
	public Integer getProgressId() {
		return progressId;
	}
	public void setProgressId(Integer progressId) {
		this.progressId = progressId;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getHopeMoney() {
		return hopeMoney;
	}
	public void setHopeMoney(Integer hopeMoney) {
		this.hopeMoney = hopeMoney;
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
	public Integer getCreateman() {
		return createman;
	}
	public void setCreateman(Integer createman) {
		this.createman = createman;
	}
	
	
}
