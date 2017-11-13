package com.fh.constant;

/**
 * Created by Administrator on 2017/11/9.
 */
public enum State {

    ONE(0),

    TWO(1);

    private Integer state;


    State(int state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
