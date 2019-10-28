package com.bill.apattern.moban;

public class Benchi extends Car{
    @Override
    protected void start() {
        System.out.println("Benchi-start-----------");
    }

    @Override
    protected void stop() {
        System.out.println("Benchi-stop-----------");
    }

    @Override
    protected void alarm() {
        System.out.println("Benchi-alarm-----------");
    }
}
