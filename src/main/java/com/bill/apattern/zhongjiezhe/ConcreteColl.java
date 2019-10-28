package com.bill.apattern.zhongjiezhe;

public abstract class ConcreteColl {

    protected AbstractMediator abstractMediator;

    public ConcreteColl(AbstractMediator abstractMediator) {
        this.abstractMediator = abstractMediator;
    }
}
