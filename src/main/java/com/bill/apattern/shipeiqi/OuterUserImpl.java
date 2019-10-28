package com.bill.apattern.shipeiqi;

import java.util.HashMap;
import java.util.Map;

public class OuterUserImpl implements OuterUser{
    @Override
    public Map<Integer,String> getOutName() {
        Map<Integer,String> map=new HashMap<>(1);
        map.put(1,"适配器模式--------OuterUserImpl--Name");
        return map;
    }

    @Override
    public Map<Integer,String> getOutAddress() {
        Map<Integer,String> map=new HashMap<>(1);
        map.put(1,"适配器模式--------OuterUserImpl--Address");
        return map;
    }
}
