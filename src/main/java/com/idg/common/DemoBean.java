package com.idg.common;

/**
 * Created by yehao on 16/7/15.
 */
public class DemoBean {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DemoBean() {
    }

    public DemoBean(String password) {
        this.password = password;
    }

    public String encrypt() {
        return password + "abcdefg";
    }
}
