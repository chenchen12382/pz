package com.fh.model;

import com.fh.base.BaseModel;

/**
 * Created by Administrator on 2017/9/14.
 */
public class Center extends BaseModel{

    private String center;
    private String district;

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
