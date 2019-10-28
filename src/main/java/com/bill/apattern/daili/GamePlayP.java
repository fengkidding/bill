package com.bill.apattern.daili;

public class GamePlayP implements GamePlay{

    private String name;

    private GamePlayProxy gamePlayProxy;

    public GamePlayP(String name){
        this.name=name;
    }

    @Override
    public void login(String user) {
        if(gamePlayProxy==null){
            System.out.println("请使用代理");
        }else{
            System.out.println("登陆="+name);
        }
    }

    @Override
    public void killBoss() {
        if(gamePlayProxy==null){
            System.out.println("请使用代理");
        }else{
            System.out.println("打怪="+name);
        }
    }

    @Override
    public GamePlay getProxy() {
        this.gamePlayProxy=new GamePlayProxy(this);
        return this.gamePlayProxy;
    }
}
