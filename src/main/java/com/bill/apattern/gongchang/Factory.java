package com.bill.apattern.gongchang;

public class Factory extends AbstractFactory {
    @Override
    public <T extends FactoryHuman> T create(Class<T> tClass) {
        FactoryHuman factoryHuman=null;
        try{
            factoryHuman= (T) Class.forName(tClass.getName()).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T) factoryHuman;
    }
}
