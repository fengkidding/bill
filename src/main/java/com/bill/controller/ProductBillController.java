package com.bill.controller;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.common.ResultVO;
import com.bill.model.vo.param.BillParamVO;
import com.bill.model.vo.param.QueryBillParamVO;
import com.bill.model.vo.param.StatisticsBillParamVO;
import com.bill.model.vo.param.QueryOrderParamVO;
import com.bill.model.vo.view.QueryProductBillVO;
import com.bill.model.vo.view.StatisticsBillVO;
import com.bill.service.ProductBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品账单接口
 *
 * @author f
 * @date 2018-04-22
 */
@Api(tags = {"商品账单接口"})
@RestController
@RequestMapping(value = "/product-bill")
public class ProductBillController extends BaseController {

    @Autowired
    private ProductBillService productBillService;

    /**
     * 分页查询商品账单列表
     *
     * @param queryBillParamVO
     * @return
     */
    @ApiOperation(value = "分页查询商品账单列表")
    @PostMapping(value = "/list-product-bill")
    public ResultVO<PageVO<List<QueryProductBillVO>>> listProductBill(@RequestBody @Valid QueryBillParamVO queryBillParamVO) {
        PageVO<List<QueryProductBillVO>> pageVmo = productBillService.listProductBill(queryBillParamVO);
        return super.resultSuccess(pageVmo);
    }

    /**
     * 统计账单
     *
     * @param statisticsBillParamVmo
     * @return
     */
    @ApiOperation(value = "统计账单")
    @PostMapping(value = "/statistics-bill")
    public ResultVO<List<StatisticsBillVO>> statisticsBill(@RequestBody @Valid StatisticsBillParamVO statisticsBillParamVmo) {
        return super.resultSuccess(productBillService.statisticsBill(statisticsBillParamVmo));
    }

    /**
     * 保存入账信息
     *
     * @param billParamVmo
     * @return
     */
    @ApiOperation(value = "保存入账信息")
    @PostMapping(value = "/save-product-bill")
    public ResultVO saveProductBill(@RequestBody @Valid BillParamVO billParamVmo) {
        LogBackUtils.info("saveProductBill-保存入账信息:billParamVmo=" + JSON.toJSONString(billParamVmo));
        productBillService.saveProductBill(billParamVmo);
        return super.resultSuccess();
    }

}
