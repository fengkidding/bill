package com.bill.apattern.shipeiqi;

public class UserInfoImpl implements UserInfo{
    @Override
    public String getName() {
        return "适配器模式--------UserInfoImpl--Name";
    }

    @Override
    public String getAddress() {
        return "适配器模式--------UserInfoImpl--Address";
    }
}
