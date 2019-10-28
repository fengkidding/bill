package com.bill.apattern.mingling;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action() {
        command.excute();
    }
}
