package com.bill.model.conversion;

import com.bill.model.po.auto.MoneyMemo;
import com.bill.model.po.ext.MoneyMemoExt;
import com.bill.model.vo.param.MoneyMemoSaveParamVO;
import com.bill.model.vo.view.MoneyMemoListVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 金额备忘转换类
 *
 * @author f
 * @date 2020-01-16
 */
@Mapper(componentModel = "spring")
public interface MoneyMemoConversion {

    MoneyMemoConversion MONEY_MEMO_CONVERSION = Mappers.getMapper(MoneyMemoConversion.class);

    /**
     * 金额备忘数据
     *
     * @param moneyMemoSaveParamVO
     * @return
     */
    MoneyMemo voToPo(MoneyMemoSaveParamVO moneyMemoSaveParamVO);

    /**
     * 金额备忘数据
     *
     * @param moneyMemoExt
     * @return
     */
    MoneyMemoListVO poToListVo(MoneyMemoExt moneyMemoExt);

    /**
     * 金额备忘数据
     *
     * @param moneyMemoExts
     * @return
     */
    List<MoneyMemoListVO> poToListVo(List<MoneyMemoExt> moneyMemoExts);
}
