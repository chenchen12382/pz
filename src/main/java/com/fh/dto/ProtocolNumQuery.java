package com.fh.dto;

import com.fh.base.BaseQuery;

/**
 * Created by Administrator on 2017/11/22.
 */
public class ProtocolNumQuery extends BaseQuery {
    private String center;
    private String bh; //编号

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }
}
