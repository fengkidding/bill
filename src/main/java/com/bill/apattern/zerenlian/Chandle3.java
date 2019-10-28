package com.bill.apattern.zerenlian;

public class Chandle3 extends Handler{
    @Override
    public int getLevel() {
        return 3;
    }

    @Override
    public String echo(Request request) {
        return "责任链模式--------------Chandle3";
    }
}
