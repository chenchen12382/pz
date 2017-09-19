package com.fh.dto;

import com.fh.base.BaseQuery;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by Administrator on 2017/9/15.
 */
public class FinanceQuery extends BaseQuery {

    private String name;
    private String  saleClass; //乐博士



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
}
