package com.bill.apattern.mingling;

public class CodeGroup extends Group {

    @Override
    public void find() {
        System.out.println("命令模式---------------find");
    }

    @Override
    public void plan() {
        System.out.println("命令模式---------------plan");
    }
}
