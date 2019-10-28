package com.bill.apattern;


import com.bill.apattern.beiwanglu.Caretaker;
import com.bill.apattern.beiwanglu.Originator;
import com.bill.apattern.celue.Caculator;
import com.bill.apattern.daili.GamePlay;
import com.bill.apattern.daili.GamePlayP;
import com.bill.apattern.danli.Singleton;
import com.bill.apattern.fangwenzhe.Element;
import com.bill.apattern.fangwenzhe.ElementImpl1;
import com.bill.apattern.fangwenzhe.ElementImpl2;
import com.bill.apattern.fangwenzhe.VisitorImpl;
import com.bill.apattern.gongchang.*;
import com.bill.apattern.guanchazhe.Hanfeizi;
import com.bill.apattern.guanchazhe.Lisi;
import com.bill.apattern.jianzao.BaomaBuilder;
import com.bill.apattern.jianzao.BenchiBuilder;
import com.bill.apattern.mingling.AddCommand;
import com.bill.apattern.mingling.Command;
import com.bill.apattern.mingling.Invoker;
import com.bill.apattern.moban.Baoma;
import com.bill.apattern.moban.Benchi;
import com.bill.apattern.moban.Car;
import com.bill.apattern.shipeiqi.AdaperUser;
import com.bill.apattern.shipeiqi.OuterUserImpl;
import com.bill.apattern.shipeiqi.UserInfoImpl;
import com.bill.apattern.yuanxing.Mail;
import com.bill.apattern.zerenlian.*;
import com.bill.apattern.zhongjiezhe.AbstractMediator;
import com.bill.apattern.zhongjiezhe.CC1;
import com.bill.apattern.zhongjiezhe.CC2;
import com.bill.apattern.zhongjiezhe.Mediator;
import com.bill.apattern.zhuangshi.ReportDecorator;
import com.bill.apattern.zuhe.Component;
import com.bill.apattern.zuhe.Composite;
import com.bill.apattern.zuhe.Leaf;

import java.util.ArrayList;
import java.util.List;

public class Testm {
    public static void main(String[] args) {
        //单例
        Singleton huangDi1 = Singleton.getHuangdi1();
        Singleton huangDi2 = Singleton.getHuangdi2();
        huangDi1.dengJi();
        huangDi2.dengJi();

        //工厂
        AbstractFactory abstractFactory = new Factory();
        FactoryHuman factoryHumanA = abstractFactory.create(FactoryHumanA.class);
        FactoryHuman factoryHumanB = abstractFactory.create(FactoryHumanB.class);
        factoryHumanA.say();
        factoryHumanB.say();

        //模版
        Car benchi = new Benchi();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        benchi.setList(list);
        benchi.run();
        Car baoma = new Baoma();
        list.remove(2);
        baoma.setList(list);
        baoma.run();

        // 建造者
        BaomaBuilder baomaBuilder = new BaomaBuilder();
        baomaBuilder.setList(list);
        Baoma baoma1 = (Baoma) baomaBuilder.getCar();
        baoma1.run();
        BenchiBuilder benchiBuilder = new BenchiBuilder();
        list.add(3);
        benchiBuilder.setList(list);
        Benchi benchi1 = (Benchi) benchiBuilder.getCar();
        benchi1.run();

        //代理
        GamePlayP gamePlayP = new GamePlayP("用户");
        gamePlayP.login("真人");
        gamePlayP.killBoss();
        GamePlay gamePlay = gamePlayP.getProxy();
        gamePlay.login("代理");
        gamePlay.killBoss();

        //原型
        int i = 5;
        Mail mail = new Mail();
        mail.setSend("原型模式-------------");
        while (i > 0) {
            Mail mail1 = mail.clone();
            mail1.setUser(i + " ");
            System.out.println(mail1.getSend() + mail1.getUser());
            i--;
        }

        //策略
        System.out.println("策略模式-------------" + Caculator.ADD.exec(1, 2));
        System.out.println("策略模式-------------" + Caculator.valueOf("SUB").exec(5, 2));

        //装饰
        ReportDecorator reportDecorator = new ReportDecorator();
        reportDecorator.report();
        reportDecorator.sign();

        //中介
        AbstractMediator abstractMediator = new Mediator();
        CC1 cc1 = new CC1(abstractMediator);
        CC2 cc2 = new CC2(abstractMediator);
        abstractMediator.setCc1(cc1);
        abstractMediator.setCc2(cc2);
        cc1.method();
        cc2.method();

        //命令
        Invoker invoker = new Invoker();
        Command command = new AddCommand();
        invoker.setCommand(command);
        invoker.action();

        //责任链
        Handler handler1 = new Chandle1();
        Handler handler2 = new Chandle2();
        Handler handler3 = new Chandle3();
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        Request request = new Request();
        request.setLevel(1);
        String zerenlian1 = handler1.handleMessage(request);
        request.setLevel(2);
        String zerenlian2 = handler1.handleMessage(request);
        request.setLevel(3);
        String zerenlian3 = handler1.handleMessage(request);
        request.setLevel(4);
        String zerenlian4 = handler1.handleMessage(request);
        System.out.println(zerenlian1);
        System.out.println(zerenlian2);
        System.out.println(zerenlian3);
        System.out.println(zerenlian4);

        //适配器
        UserInfoImpl userInfo = new UserInfoImpl();
        System.out.println(userInfo.getName());
        System.out.println(userInfo.getAddress());
        OuterUserImpl outerUser = new OuterUserImpl();
        System.out.println(outerUser.getOutName().get(1));
        System.out.println(outerUser.getOutAddress().get(1));
        AdaperUser adaperUser = new AdaperUser();
        System.out.println(adaperUser.getName());
        System.out.println(adaperUser.getAddress());

        //组合
        Composite composite = new Composite("总裁");
        Composite compositeCrop = new Composite("经理");
        Composite compositeCrop1 = new Composite("经理1");
        Component leaf = new Leaf("员工");
        Component leaf1 = new Leaf("员工1");
        composite.add(compositeCrop);
        composite.add(compositeCrop1);
        compositeCrop.add(leaf);
        compositeCrop.add(leaf);
        compositeCrop.add(leaf);
        compositeCrop.add(leaf1);
        compositeCrop.add(leaf1);
        compositeCrop.add(leaf1);
        compositeCrop.add(leaf1);
        getTree(composite);

        //观察者
        com.bill.apattern.guanchazhe.Observer lisi = new Lisi();
        Hanfeizi hanfeizi = new Hanfeizi();
        hanfeizi.addO(lisi);
        hanfeizi.sport();

        //备忘录
        Originator originator = new Originator();
        System.out.println("备忘录模式--------" + originator.getState());
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("改变");
        System.out.println("备忘录模式--------" + originator.getState());
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("备忘录模式--------" + originator.getState());

        //访问者模式
        Element element1 = new ElementImpl1();
        Element element2 = new ElementImpl2();
        element1.accept(new VisitorImpl());
        element2.accept(new VisitorImpl());

        //synchronized加锁
        Object lock=new Object();
        new Thread(()->{
            synchronized(lock){
                System.out.println("thread1-");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        new Thread(()->{
            synchronized(lock){
                System.out.println("thread2-");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }

    /**
     * 组合模式遍历信息
     *
     * @param composite
     */
    public static void getTree(Composite composite) {
        System.out.println("");
        composite.Operation();
        System.out.println("");
        for (Component c : composite.getCorp()) {
            if (c instanceof Leaf) {
                c.Operation();
            } else {
                getTree((Composite) c);
            }
        }
    }
}
