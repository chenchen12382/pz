package com.fh.model;

import com.fh.base.BaseModel;

/**
 * Created by Administrator on 2017/9/14.
 */
public class PriceClass extends BaseModel {
    private String saleClass;
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSaleClass() {
        return saleClass;
    }

    public void setSaleClass(String saleClass) {
        this.saleClass = saleClass;
    }
}
