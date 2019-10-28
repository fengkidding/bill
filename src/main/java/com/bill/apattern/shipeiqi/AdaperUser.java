package com.bill.apattern.shipeiqi;

import java.util.Map;

public class AdaperUser extends OuterUserImpl implements UserInfo{

    private Map<Integer,String> name=super.getOutName();
    private Map<Integer,String> address=super.getOutAddress();

    @Override
    public String getName() {
        return name.get(1);
    }

    @Override
    public String getAddress() {
        return address.get(1);
    }
}
