package com.fh.dto;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fh.base.BaseQuery;

public class AnalyzeTotalQuery extends BaseQuery {
	  @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date start;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date over;
		private String center;  //中心
	
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
	
	    public String getCenter() {
	        return center;
	    }
	
	    public void setCenter(String center) {
	        this.center = center;
	    }

}

