package com.bill.service;


import com.bill.model.po.auto.ProductOrder;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.OrderParamVO;
import com.bill.model.vo.param.PayOrderParamVO;
import com.bill.model.vo.param.QueryOrderParamVO;
import com.bill.model.vo.view.QueryOrderVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单接口
 *
 * @author f
 * @date 2019-03-10
 */
public interface OrderService {

    /**
     * 用户下单
     *
     * @param orderParamVmo
     */
    Integer createOrder(OrderParamVO orderParamVmo);

    /**
     * 分页根据用户查询订单
     *
     * @param memberId
     * @return
     */
    PageVO<List<QueryOrderVO>> listOrder(QueryOrderParamVO orderPageParamVmo, Integer memberId);

    /**
     * 根据用户查询订单
     *
     * @param memberId
     * @return
     */
    List<ProductOrder> listProductOrder(Integer memberId, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 根据id查询订单
     *
     * @param orderId
     * @return
     */
    ProductOrder getProductOrder(Integer orderId);

    /**
     * 更新订单
     *
     * @param productOrder
     */
    void updateOrder(ProductOrder productOrder);

    /**
     * 支付订单
     *
     * @param payOrderParamVO
     */
    void payOrder(PayOrderParamVO payOrderParamVO);
}
