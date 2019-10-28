package com.bill.apattern.fangwenzhe;

public interface Element {
    public abstract void doSome();
    abstract void accept(InterfaceVisitor iVisitor);
}
