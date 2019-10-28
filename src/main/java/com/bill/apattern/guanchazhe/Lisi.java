package com.bill.apattern.guanchazhe;

public class Lisi implements Observer {
    @Override
    public void doSome(String content) {
        System.out.println("观察者模式---------------李斯：" + content);
    }
}
