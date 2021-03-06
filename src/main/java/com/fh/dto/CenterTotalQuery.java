package com.fh.dto;

import com.fh.base.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/29.
 */
public class CenterTotalQuery extends BaseQuery {
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date over;

    private  String center;

    private Integer time; //前台数据 0为当天 1为当月

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
