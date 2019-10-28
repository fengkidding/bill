package com.bill.apattern.moban;

import java.util.ArrayList;
import java.util.List;

public abstract class Car {

    protected List<Integer> list=new ArrayList<Integer>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    public final void run(){
        list.forEach(integer->{
            if(integer.equals(1)){
                this.start();
            }else if(integer.equals(2)){
                this.stop();
            }else if(integer.equals(3)){
                this.alarm();
            }
        });

    }

    public final void setList(List<Integer> list){
        this.list=list;
    }
}
