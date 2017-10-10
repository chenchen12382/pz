package com.fh.model;

import com.fh.base.BaseModel;

/**
 * Created by Administrator on 2017/10/9.
 */
public class ReportCount extends BaseModel {
    private String district; //区域
    private Integer income;  //总收入/完成业绩
    private Integer target; //指标
    private Integer discount; //完成率

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
