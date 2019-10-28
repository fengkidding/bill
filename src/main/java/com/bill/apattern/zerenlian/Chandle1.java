package com.bill.apattern.zerenlian;

public class Chandle1 extends Handler{
    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public String echo(Request request) {
        return "责任链模式--------------Chandle1";
    }
}
