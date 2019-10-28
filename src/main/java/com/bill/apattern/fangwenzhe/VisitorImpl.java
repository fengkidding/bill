package com.bill.apattern.fangwenzhe;

public class VisitorImpl implements InterfaceVisitor {
    @Override
    public void visit(ElementImpl1 elementImpl1) {
        elementImpl1.doSome();
    }

    @Override
    public void visit(ElementImpl2 elementImpl2) {
        elementImpl2.doSome();
    }
}
