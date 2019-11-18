package com.bill.controller;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.common.util.AuthContextUtils;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.common.ResultVO;
import com.bill.model.vo.param.OrderParamVO;
import com.bill.model.vo.param.QueryOrderParamVO;
import com.bill.model.vo.view.QueryOrderVO;
import com.bill.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        LogBackUtils.info("更新用户余额: productSaveParamVmo=" + JSON.toJSONString(orderParamVmo));
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
    public ResultVO<PageVO<List<QueryOrderVO>>> listOrder(@Valid @RequestBody QueryOrderParamVO orderPageParamVmo) {
        Integer memberId = AuthContextUtils.getLoginMemberId();
        PageVO<List<QueryOrderVO>> pageVmo = orderService.listOrder(orderPageParamVmo, memberId);
        return super.resultSuccess(pageVmo);
    }

}
