package com.bill.service;


import com.bill.model.entity.auto.ProductOrder;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.OrderParamVO;
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
     * @param userName
     * @return
     */
    PageVO<List<QueryOrderVO>> listOrder(Integer pageNum, Integer pageSize, String userName);

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
