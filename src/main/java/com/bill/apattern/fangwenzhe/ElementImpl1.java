package com.bill.apattern.fangwenzhe;

public class ElementImpl1 implements Element {
    @Override
    public void doSome() {
        System.out.println("访问者模式---------------ElementImpl1");
    }

    @Override
    public void accept(InterfaceVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
