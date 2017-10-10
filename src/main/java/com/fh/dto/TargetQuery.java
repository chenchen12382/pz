package com.fh.dto;

import com.fh.base.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class TargetQuery extends BaseQuery{
	
	 private String district;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date month;
	
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
	
   
}
