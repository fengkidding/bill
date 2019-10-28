package com.bill.apattern.yuanxing;

public class Mail implements Cloneable {
    private String user;
    private Integer money;
    private String send;
    private String remark;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Mail clone() {
        Mail mail = null;
        try {
            mail = (Mail) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mail;
    }
}
