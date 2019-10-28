package com.bill.apattern.zhongjiezhe;

public class CC1 extends ConcreteColl {
    public CC1(AbstractMediator abstractMediator) {
        super(abstractMediator);
    }

    public void method(){
        System.out.println("中介者模式-----------------自行方法");
    }
}
