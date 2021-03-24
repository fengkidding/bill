package com.bill.controller;

import com.bill.model.constant.NumberConstant;
import com.bill.model.conversion.MoneyMemoConversion;
import com.bill.model.po.auto.MoneyMemo;
import com.bill.model.po.ext.MoneyMemoExt;
import com.bill.model.vo.common.ResultVO;
import com.bill.model.vo.param.MoneyMemoParamVO;
import com.bill.model.vo.param.MoneyMemoSaveParamVO;
import com.bill.model.vo.view.MoneyMemoListVO;
import com.bill.service.MoneyMemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 金额备忘api
 *
 * @author f
 * @date 2019-10-10
 */
@Api(tags = {"金额备忘api"})
@RestController
@RequestMapping(value = "/money-memo")
public class MoneyMemoController extends BaseController {

    @Autowired
    private MoneyMemoService moneyMemoService;

    /**
     * 保存资产金额备忘接口
     *
     * @param moneyMemoSaveParamVO
     * @return
     */
    @ApiOperation(value = "保存资产金额备忘接口")
    @PostMapping(value = "/save-money-memo")
    public ResultVO saveMoneyMemo(@Valid MoneyMemoSaveParamVO moneyMemoSaveParamVO) {
        MoneyMemo moneyMemo = MoneyMemoConversion.MONEY_MEMO_CONVERSION.voToPo(moneyMemoSaveParamVO);
        moneyMemoService.saveMoneyMemo(moneyMemo);
        return super.resultSuccess();
    }

    /**
     * 保存负债金额备忘接口
     *
     * @param moneyMemoSaveParamVO
     * @return
     */
    @ApiOperation(value = "保存负债金额备忘接口")
    @PostMapping(value = "/save-money-memo-debt")
    public ResultVO saveMoneyMemoDebt(@Valid MoneyMemoSaveParamVO moneyMemoSaveParamVO) {
        MoneyMemo moneyMemo = MoneyMemoConversion.MONEY_MEMO_CONVERSION.voToPo(moneyMemoSaveParamVO);
        moneyMemo.setMoneyType(NumberConstant.BYTE_ONE);
        moneyMemoService.saveMoneyMemo(moneyMemo);
        return super.resultSuccess();
    }

    /**
     * 统计金额备忘接口
     *
     * @param moneyMemoParamVO
     * @return
     */
    @ApiOperation(value = "统计金额备忘接口")
    @PostMapping(value = "/list-money-memo-list-vo")
    public ResultVO<List<MoneyMemoListVO>> listMoneyMemoListVO(@Valid @RequestBody MoneyMemoParamVO moneyMemoParamVO) {
        List<MoneyMemoExt> moneyMemoExts = moneyMemoService.listMoneyMemo(moneyMemoParamVO);
        return super.resultSuccess(MoneyMemoConversion.MONEY_MEMO_CONVERSION.poToListVo(moneyMemoExts));
    }
}
