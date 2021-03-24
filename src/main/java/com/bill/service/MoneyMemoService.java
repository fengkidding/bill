package com.bill.service;

import com.bill.model.po.auto.MoneyMemo;
import com.bill.model.po.ext.MoneyMemoExt;
import com.bill.model.vo.param.MoneyMemoParamVO;

import java.util.List;

/**
 * 金额备忘service
 *
 * @author f
 * @date 2020-01-16
 */
public interface MoneyMemoService {

    /**
     * 保存金额备忘
     *
     * @param moneyMemo
     */
    void saveMoneyMemo(MoneyMemo moneyMemo);

    /**
     * 统计金额备忘
     *
     * @param moneyMemoParamVO
     * @return
     */
    List<MoneyMemoExt> listMoneyMemo(MoneyMemoParamVO moneyMemoParamVO);
}
