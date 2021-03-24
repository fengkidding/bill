package com.bill.dao.db.ext;

import com.bill.dao.db.auto.MoneyMemoMapper;
import com.bill.model.po.ext.MoneyMemoExt;
import com.bill.model.vo.param.MoneyMemoParamVO;

import java.util.List;

/**
 * 金额备忘mapper
 *
 * @author f
 * @date 2020-01-16
 */
public interface MoneyMemoExtMapper extends MoneyMemoMapper {

    /**
     * 统计金额备忘
     *
     * @param moneyMemoParamVO
     * @return
     */
    List<MoneyMemoExt> listMoneyMemo(MoneyMemoParamVO moneyMemoParamVO);
}