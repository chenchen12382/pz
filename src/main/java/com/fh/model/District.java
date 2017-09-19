package com.fh.model;

import com.fh.base.BaseModel;

/**
 * Created by Administrator on 2017/9/18.
 */
public class District extends BaseModel{

    private String district;
    private String remark;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
