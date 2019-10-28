package com.bill.apattern.mingling;

public class AddCommand extends Command {
    @Override
    public void excute() {
        super.codeGroup.find();
        super.codeGroup.plan();
    }
}
