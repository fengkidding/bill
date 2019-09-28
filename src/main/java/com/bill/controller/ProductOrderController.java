package com.bill.controller;

import com.alibaba.fastjson.JSON;
import com.bill.model.vmo.common.PageVmo;
import com.bill.model.vmo.common.ResultVmo;
import com.bill.model.vmo.param.OrderParamVmo;
import com.bill.model.vmo.param.StatisticsOrderMoney;
import com.bill.model.vmo.param.UserPageParamVmo;
import com.bill.model.vmo.view.OrderMoneyVmo;
import com.bill.model.vmo.view.QueryOrder;
import com.bill.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 订单接口
 *
 * @author f
 * @date 2018-04-22
 */
@Api(description = "订单接口")
@RestController
@RequestMapping(value = "/product_order")
public class ProductOrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductOrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     *
     * @param orderParamVmo
     * @param request
     * @return
     */
    @ApiOperation(value = "用户下单")
    @PostMapping(value = "/create_order")
    public ResultVmo createOrder(@RequestBody @Valid OrderParamVmo orderParamVmo, HttpServletRequest request) {
        logger.info("更新用户余额: traceId=" + request.getAttribute("traceId") + ",productSaveParamVmo=" + JSON.toJSONString(orderParamVmo));
        Integer id = orderService.createOrder(orderParamVmo);
        return super.resultSuccess(id);
    }

    /**
     * 分页查询订单列表
     *
     * @param orderPageParamVmo
     * @return
     */
    @ApiOperation(value = "分页查询订单列表")
    @PostMapping(value = "/list_order")
    public ResultVmo<PageVmo<List<QueryOrder>>> listOrder(@Valid @RequestBody UserPageParamVmo orderPageParamVmo) {
        PageVmo<List<QueryOrder>> pageVmo = orderService.listOrder(orderPageParamVmo.getPageNum(), orderPageParamVmo.getPageSize(), orderPageParamVmo.getUserName());
        return super.resultSuccess(pageVmo);
    }

    /**
     * 统计订单金额接口
     *
     * @param statisticsOrderMoney
     * @return
     */
    @ApiOperation(value = "统计订单金额接口")
    @PostMapping(value = "/get_order_money")
    public ResultVmo<List<OrderMoneyVmo>> getOrderMoney(@RequestBody @Valid StatisticsOrderMoney statisticsOrderMoney) {
        return super.resultSuccess(orderService.getOrderMoney(statisticsOrderMoney));
    }
}
