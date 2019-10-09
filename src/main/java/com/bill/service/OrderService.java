package com.bill.service;


import com.bill.model.entity.auto.ProductOrder;
import com.bill.model.vmo.common.PageVmo;
import com.bill.model.vmo.param.OrderParamVmo;
import com.bill.model.vmo.view.QueryOrder;

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
    Integer createOrder(OrderParamVmo orderParamVmo);

    /**
     * 分页根据用户查询订单
     *
     * @param userName
     * @return
     */
    PageVmo<List<QueryOrder>> listOrder(Integer pageNum, Integer pageSize, String userName);

    /**
     * 根据用户查询订单
     *
     * @param userName
     * @return
     */
    List<ProductOrder> listProductOrder(String userName, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 根据id查询订单
     *
     * @param orderId
     * @return
     */
    ProductOrder getProductOrder(Integer orderId);
}
