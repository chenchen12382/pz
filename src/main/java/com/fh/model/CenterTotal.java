package com.fh.model;

import com.fh.base.BaseModel;

/**
 * Created by Administrator on 2017/9/29.
 */

/**
 * 中心业绩统计
 */
public class CenterTotal extends BaseModel {

    private String district; //区域
    private String center ; //中心
    private Integer orderTotal; //总订单
    private Integer shouldTotal; //总应收
    private Integer realTotal; //总实收
    private String discount ;  //平均折扣

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

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getShouldTotal() {
        return shouldTotal;
    }

    public void setShouldTotal(Integer shouldTotal) {
        this.shouldTotal = shouldTotal;
    }

    public Integer getRealTotal() {
        return realTotal;
    }

    public void setRealTotal(Integer realTotal) {
        this.realTotal = realTotal;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
