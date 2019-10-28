package com.bill.apattern.zerenlian;

public class Chandle2 extends Handler{
    @Override
    public int getLevel() {
        return 2;
    }

    @Override
    public String echo(Request request) {
        return "责任链模式--------------Chandle2";
    }
}
