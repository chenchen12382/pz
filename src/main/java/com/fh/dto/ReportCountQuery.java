package com.fh.dto;

import com.fh.base.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/29.
 */
public class ReportCountQuery extends BaseQuery {
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date over;

    private  String district;  //区域

    private String center; //中心

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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }
}
