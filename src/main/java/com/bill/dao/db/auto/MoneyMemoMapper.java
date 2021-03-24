package com.bill.dao.db.auto;

import com.bill.model.po.auto.MoneyMemo;

public interface MoneyMemoMapper {
    int deleteByPrimaryKey(Integer moneyMemoId);

    int insert(MoneyMemo record);

    int insertSelective(MoneyMemo record);

    MoneyMemo selectByPrimaryKey(Integer moneyMemoId);

    int updateByPrimaryKeySelective(MoneyMemo record);

    int updateByPrimaryKey(MoneyMemo record);
}