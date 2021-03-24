package com.bill.service.impl;

import com.bill.dao.db.ext.MoneyMemoExtMapper;
import com.bill.model.constant.BillConstant;
import com.bill.model.constant.CommonConstant;
import com.bill.model.constant.NumberConstant;
import com.bill.model.po.auto.MoneyMemo;
import com.bill.model.po.ext.MoneyMemoExt;
import com.bill.model.vo.param.MoneyMemoParamVO;
import com.bill.service.MoneyMemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 金额备忘service
 *
 * @author f
 * @date 2020-01-16
 */
@Service
public class MoneyMemoServiceImpl implements MoneyMemoService {

    @Autowired
    private MoneyMemoExtMapper moneyMemoExtMapper;

    /**
     * 保存金额备忘
     *
     * @param moneyMemo
     */
    @Override
    public void saveMoneyMemo(MoneyMemo moneyMemo) {
        moneyMemoExtMapper.insertSelective(moneyMemo);
    }

    /**
     * 统计金额备忘
     *
     * @param moneyMemoParamVO
     * @return
     */
    @Override
    public List<MoneyMemoExt> listMoneyMemo(MoneyMemoParamVO moneyMemoParamVO) {
        List<MoneyMemoExt> list = moneyMemoExtMapper.listMoneyMemo(moneyMemoParamVO);
        if (!CollectionUtils.isEmpty(list)) {
            BigDecimal bigDecimal = BigDecimal.ZERO;
            for (MoneyMemoExt item : list) {
                if (NumberConstant.BYTE_ONE.equals(item.getMoneyType())) {
                    bigDecimal = bigDecimal.subtract(item.getAmountSum());
                    item.setRemark(BillConstant.DEBT + item.getAmountSum() + "," + BillConstant.EQUITY + (item.getAmountSum().negate()));
                } else if (NumberConstant.BYTE_ZERO.equals(item.getMoneyType())) {
                    bigDecimal = bigDecimal.add(item.getAmountSum());
                    item.setRemark(BillConstant.ASSETS + item.getAmountSum() + "," + BillConstant.EQUITY + (item.getAmountSum()));
                }
            }
            MoneyMemoExt moneyMemoExt = new MoneyMemoExt();
            moneyMemoExt.setAmountSum(bigDecimal);
            moneyMemoExt.setMemberId(moneyMemoParamVO.getMemberId());
            moneyMemoExt.setDescription(BillConstant.AMOUNT_ALL);
            moneyMemoExt.setRemark(BillConstant.EQUITY + bigDecimal);
            list.add(moneyMemoExt);
            return list;
        }
        return CommonConstant.EMPTY_LIST;
    }

}
