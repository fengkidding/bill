package com.bill.apattern.zhongjiezhe;

public abstract class AbstractMediator {

    protected CC1 cc1;

    protected CC2 cc2;

    public CC1 getCc1() {
        return cc1;
    }

    public void setCc1(CC1 cc1) {
        this.cc1 = cc1;
    }

    public CC2 getCc2() {
        return cc2;
    }

    public void setCc2(CC2 cc2) {
        this.cc2 = cc2;
    }

    public abstract void doSomeThing1();

    public abstract void doSomeThing2();
}
