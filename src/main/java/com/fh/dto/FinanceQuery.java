package com.fh.dto;

import com.fh.base.BaseQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/15.
 */
public class FinanceQuery extends BaseQuery {

    private String name;
    private String saleClass; //乐博士
    private String center;
    private String userCenter;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date over;

    private String sPayMode; //支付方式

    private String sProperty; // 付款性质


    public String getUserCenter() {
        return userCenter;
    }

    public void setUserCenter(String userCenter) {
        this.userCenter = userCenter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaleClass() {
        return saleClass;
    }

    public void setSaleClass(String saleClass) {
        this.saleClass = saleClass;
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

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getsPayMode() {
        return sPayMode;
    }

    public void setsPayMode(String sPayMode) {
        this.sPayMode = sPayMode;
    }

    public String getsProperty() {
        return sProperty;
    }

    public void setsProperty(String sProperty) {
        this.sProperty = sProperty;
    }
}
