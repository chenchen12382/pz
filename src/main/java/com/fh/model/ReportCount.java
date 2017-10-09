package com.fh.model;

import com.fh.base.BaseModel;

/**
 * Created by Administrator on 2017/10/9.
 */
public class ReportCount extends BaseModel {
    private String district; //区域
    private String income;  //总收入/完成业绩
    private String target; //指标
    private String discount; //完成率

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
