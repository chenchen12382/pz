package com.fh.model;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by Administrator on 2017/10/12.
 */
public class DistrictCharts {
    private String district;  //区域
    private String total;     //总数
    private Integer finish; //完成率

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }
}
