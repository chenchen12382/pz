package com.fh.model;

import com.fh.base.BaseModel;

/**
 * Created by Administrator on 2017/10/25.
 */
public class UpLog extends BaseModel{
    private String title;
    private String log;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
