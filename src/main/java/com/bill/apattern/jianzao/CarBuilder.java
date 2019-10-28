package com.bill.apattern.jianzao;

import com.bill.apattern.moban.Car;

import java.util.List;

public abstract class CarBuilder {
    public abstract void setList(List<Integer> list);

    public abstract Car getCar();
}
