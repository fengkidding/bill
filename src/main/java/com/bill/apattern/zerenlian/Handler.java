package com.bill.apattern.zerenlian;

public abstract class Handler {
    private Handler next;

    public final String handleMessage(Request request) {
        String response = null;
        if (this.getLevel() == request.getLevel()) {
            response = this.echo(request);
        } else {
            if (next != null) {
                response = this.next.handleMessage(request);
            } else {
                response = "责任链模式--------------未找到";
            }
        }
        return response;
    }

    public void setNext(Handler handler) {
        this.next = handler;
    }

    public abstract int getLevel();

    public abstract String echo(Request request);

}
