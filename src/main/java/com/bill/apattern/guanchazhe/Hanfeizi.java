package com.bill.apattern.guanchazhe;

import java.util.ArrayList;

public class Hanfeizi implements IHanfeizi, Obervable {

    private ArrayList<Observer> list = new ArrayList<>(3);

    @Override
    public void sport() {
        System.out.println("观察者模式---------------韩非子打羽毛球了");
        this.notify("韩非子在打羽毛球");
    }

    @Override
    public void addO(Observer observer) {
        this.list.add(observer);
    }

    @Override
    public void removeO(Observer observer) {
        this.list.remove(observer);
    }

    @Override
    public void notify(String content) {
        this.list.forEach(item -> item.doSome(content));
    }
}
