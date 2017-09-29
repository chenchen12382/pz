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
