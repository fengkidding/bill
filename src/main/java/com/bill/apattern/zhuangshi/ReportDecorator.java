package com.bill.apattern.zhuangshi;

public class ReportDecorator extends Report {
    @Override
    public void report() {
        super.report();
        this.decorator();
    }

    @Override
    public void sign() {
        System.out.println("装饰模式-------sign");
    }

    public void decorator() {
        System.out.println("装饰模式-------装饰一下");
    }
}
