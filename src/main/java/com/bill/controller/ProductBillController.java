package com.bill.controller;

import com.alibaba.fastjson.JSON;
import com.bill.model.vmo.common.PageVmo;
import com.bill.model.vmo.common.ResultVmo;
import com.bill.model.vmo.param.BillParamVmo;
import com.bill.model.vmo.param.StatisticsBillParamVmo;
import com.bill.model.vmo.param.UserPageParamVmo;
import com.bill.model.vmo.view.QueryProductBill;
import com.bill.model.vmo.view.StatisticsBillVmo;
import com.bill.service.ProductBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Api(description = "商品账单接口")
@RestController
@RequestMapping(value = "/product_bill")
public class ProductBillController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductBillController.class);

    @Autowired
    private ProductBillService productBillService;

    /**
     * 分页查询商品账单列表
     *
     * @param userPageParamVmo
     * @return
     */
    @ApiOperation(value = "分页查询商品账单列表")
    @PostMapping(value = "/list_product_bill")
    public ResultVmo<PageVmo<List<QueryProductBill>>> listProductBill(@RequestBody @Valid UserPageParamVmo userPageParamVmo) {
        PageVmo<List<QueryProductBill>> pageVmo = productBillService.listProductBill(userPageParamVmo);
        return super.resultSuccess(pageVmo);
    }

    /**
     * 统计账单
     *
     * @param statisticsBillParamVmo
     * @return
     */
    @ApiOperation(value = "统计账单")
    @PostMapping(value = "/statistics_bill")
    public ResultVmo<List<StatisticsBillVmo>> statisticsBill(@RequestBody @Valid StatisticsBillParamVmo statisticsBillParamVmo) {
        return super.resultSuccess(productBillService.statisticsBill(statisticsBillParamVmo));
    }

    /**
     * 保存入账信息
     *
     * @param billParamVmo
     * @return
     */
    @ApiOperation(value = "保存入账信息")
    @PostMapping(value = "/save_product_bill")
    public ResultVmo saveProductBill(@RequestBody @Valid BillParamVmo billParamVmo) {
        logger.info("saveProductBill-保存入账信息:billParamVmo=" + JSON.toJSONString(billParamVmo));
        productBillService.saveProductBill(billParamVmo);
        return super.resultSuccess();
    }

}
