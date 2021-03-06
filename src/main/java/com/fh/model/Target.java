package com.fh.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.base.BaseModel;

public class Target  extends BaseModel{
	
	    private String district;//区域
	    @JsonFormat(pattern="yyyy-MM")
	    @DateTimeFormat(pattern="yyyy-MM")
	    private Date month;//月份
	    private String months;
	    public String getMonths() {
			return months;
		}
		public void setMonths(String months) {
			this.months = months;
		}
		private Integer target;//指标
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
		public Integer getTarget() {
			return target;
		}
		public void setTarget(Integer target) {
			this.target = target;
		}
	    
	    

}
