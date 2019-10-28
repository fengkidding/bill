package com.bill.apattern.guanchazhe;

public interface Obervable {
    void addO(Observer observer);

    void removeO(Observer observer);

    void notify(String content);
}
