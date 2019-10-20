package com.bill.controller;

import com.alibaba.fastjson.JSON;
import com.bill.common.util.LogUtils;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.common.ResultVO;
import com.bill.model.vo.param.OrderParamVO;
import com.bill.model.vo.param.UserPageParamVO;
import com.bill.model.vo.view.QueryOrderVO;
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
    public ResultVO createOrder(@RequestBody @Valid OrderParamVO orderParamVmo, HttpServletRequest request) {
        LogUtils.info("更新用户余额: traceId=" + request.getAttribute("traceId") + ",productSaveParamVmo=" + JSON.toJSONString(orderParamVmo));
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
    public ResultVO<PageVO<List<QueryOrderVO>>> listOrder(@Valid @RequestBody UserPageParamVO orderPageParamVmo) {
        PageVO<List<QueryOrderVO>> pageVmo = orderService.listOrder(orderPageParamVmo.getPageNum(), orderPageParamVmo.getPageSize(), orderPageParamVmo.getUserName());
        return super.resultSuccess(pageVmo);
    }

}
