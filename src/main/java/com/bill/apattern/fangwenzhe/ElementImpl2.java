package com.bill.apattern.fangwenzhe;

public class ElementImpl2 implements Element {
    @Override
    public void doSome() {
        System.out.println("访问者模式---------------ElementImpl2");
    }

    @Override
    public void accept(InterfaceVisitor iVisitor) {
        iVisitor.visit(this);
    }
}
