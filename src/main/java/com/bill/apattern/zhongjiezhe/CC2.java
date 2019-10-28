package com.bill.apattern.zhongjiezhe;

public class CC2 extends ConcreteColl{
    public CC2(AbstractMediator abstractMediator) {
        super(abstractMediator);
    }
    public void method(){
        System.out.println("中介者模式-----------------依赖");
        super.abstractMediator.doSomeThing1();
    }
}
