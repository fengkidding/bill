package com.bill.apattern.jianzao;

import com.bill.apattern.moban.Baoma;
import com.bill.apattern.moban.Car;

import java.util.List;

public class BaomaBuilder extends CarBuilder{

    private Baoma baoma=new Baoma();

    @Override
    public void setList(List<Integer> list) {
        this.baoma.setList(list);
    }

    @Override
    public Car getCar() {
        return baoma;
    }
}
