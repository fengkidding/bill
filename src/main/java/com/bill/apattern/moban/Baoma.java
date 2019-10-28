package com.bill.apattern.moban;

public class Baoma extends Car{
    @Override
    protected void start() {
        System.out.println("Baoma-start----------");
    }

    @Override
    protected void stop() {
        System.out.println("Baoma-stop----------");
    }

    @Override
    protected void alarm() {
        System.out.println("Baoma-alarm----------");
    }
}
