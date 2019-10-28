package com.bill.apattern.gongchang;

public abstract class AbstractFactory {
    public abstract <T extends FactoryHuman> T create(Class<T> tClass);
}
