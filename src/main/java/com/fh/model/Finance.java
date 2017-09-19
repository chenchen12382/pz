package com.fh.model;

import com.fh.base.BaseModel;


/**
 * Created by Administrator on 2017/9/14.
 */
public class Finance  extends BaseModel{


    private  String xybh;      //协议编号
    private String sjbh; //收据编号
    private String hybh; //会员编号
    private String name; //会员姓名
    private String saleClass; //乐博士
    private Integer saleNum;  //销售数量
    private String agreement; //协议签订类型
    private Integer price;   //价格
    private Integer shouldMoney;  //应收金额
    private Integer realMoney;  //实际金额
    private String discount; //折扣
    private String payMode; //支付方式
    private Integer cardNum; //银行卡号
    private String counselor; //顾问
    private String  promotion; //促销
    private String gift;  //赠送课程
    private String source ; //来源

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

    public String getHybh() {
        return hybh;
    }

    public void setHybh(String hybh) {
        this.hybh = hybh;
    }

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

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getShouldMoney() {
        return shouldMoney;
    }

    public void setShouldMoney(Integer shouldMoney) {
        this.shouldMoney = shouldMoney;
    }

    public Integer getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(Integer realMoney) {
        this.realMoney = realMoney;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
