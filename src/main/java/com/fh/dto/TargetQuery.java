package com.fh.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.base.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class TargetQuery extends BaseQuery{
	
	 private String district;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	 private Date month;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date start;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date over;

	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getOver() {
		return over;
	}

	public void setOver(Date over) {
		this.over = over;
	}
}
