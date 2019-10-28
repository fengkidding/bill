package com.bill.apattern.daili;

public class GamePlayProxy implements GamePlay{

    private GamePlayP gamePlayP;

    public GamePlayProxy(GamePlayP gamePlayP){
        this.gamePlayP=gamePlayP;
    }

    @Override
    public void login(String user) {
        this.gamePlayP.login(user);
    }

    @Override
    public void killBoss() {
        this.gamePlayP.killBoss();
    }

    @Override
    public GamePlay getProxy() {
        return this;
    }
}
