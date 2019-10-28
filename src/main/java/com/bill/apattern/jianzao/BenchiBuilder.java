package com.bill.apattern.jianzao;

import com.bill.apattern.moban.Benchi;
import com.bill.apattern.moban.Car;

import java.util.List;

public class BenchiBuilder extends CarBuilder {

    private Benchi benchi = new Benchi();

    @Override
    public void setList(List<Integer> list) {
        this.benchi.setList(list);
    }

    @Override
    public Car getCar() {
        return benchi;
    }
}
