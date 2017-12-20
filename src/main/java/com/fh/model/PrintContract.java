package com.fh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.base.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/12/20.
 */
public class PrintContract extends BaseModel{
  private String centerName;
  private String contractType;
  private String fName;
  private Integer fPhone;
  private  String fMail;
  private String fWork;
  private String mName;
  private Integer mPhone;
  private  String mMail;
  private  String mWork;
  private String bName;
  private String bNikeName;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private  Date bBirthday;
    private  Integer bSex;
    private  String classNum;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private  Integer giveClass;
    private  Integer giveTicket;

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Integer getfPhone() {
        return fPhone;
    }

    public void setfPhone(Integer fPhone) {
        this.fPhone = fPhone;
    }

    public String getfMail() {
        return fMail;
    }

    public void setfMail(String fMail) {
        this.fMail = fMail;
    }

    public String getfWork() {
        return fWork;
    }

    public void setfWork(String fWork) {
        this.fWork = fWork;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Integer getmPhone() {
        return mPhone;
    }

    public void setmPhone(Integer mPhone) {
        this.mPhone = mPhone;
    }

    public String getmMail() {
        return mMail;
    }

    public void setmMail(String mMail) {
        this.mMail = mMail;
    }

    public String getmWork() {
        return mWork;
    }

    public void setmWork(String mWork) {
        this.mWork = mWork;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbNikeName() {
        return bNikeName;
    }

    public void setbNikeName(String bNikeName) {
        this.bNikeName = bNikeName;
    }

    public Date getbBirthday() {
        return bBirthday;
    }

    public void setbBirthday(Date bBirthday) {
        this.bBirthday = bBirthday;
    }

    public Integer getbSex() {
        return bSex;
    }

    public void setbSex(Integer bSex) {
        this.bSex = bSex;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getGiveClass() {
        return giveClass;
    }

    public void setGiveClass(Integer giveClass) {
        this.giveClass = giveClass;
    }

    public Integer getGiveTicket() {
        return giveTicket;
    }

    public void setGiveTicket(Integer giveTicket) {
        this.giveTicket = giveTicket;
    }
}
