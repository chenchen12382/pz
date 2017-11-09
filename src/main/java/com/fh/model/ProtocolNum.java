package com.fh.model;

import com.fh.base.BaseModel;

/**
 * Created by Administrator on 2017/11/7.
 */
public class ProtocolNum extends BaseModel{
    private Integer centerId;
    private String xybh;
    private String sjbh;

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getXybh() {
        return xybh;
    }

    public void setXybh(String xybh) {
        this.xybh = xybh;
    }

    public String getSjbh() {
        return sjbh;
    }

    public void setSjbh(String sjbh) {
        this.sjbh = sjbh;
    }
}
