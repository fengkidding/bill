package com.bill.apattern.danli;

/**
 * 单例模式
 */
public class Singleton {

    public static Singleton huangdi1=new Singleton();

    public static Singleton huangdi2=null;

    private Singleton(){

    }

    public static Singleton getHuangdi1(){
        return huangdi1;
    }

    public static Singleton getHuangdi2(){
        if(huangdi2==null){
            synchronized(Singleton.class){
                if(huangdi2==null){
                    huangdi2 = new Singleton();
                }
            }
        }
        return huangdi2;
    }

    public void dengJi(){
        System.out.println(this.toString()+"皇上登基了------");
    }
}
