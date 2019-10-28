package com.bill.apattern.zuhe;

public abstract class Component {

    protected String info;

    public void Operation() {
        System.out.println("组合模式------------" + info);
    }
}
