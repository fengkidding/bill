package com.bill.dao.db.ext;

import com.bill.model.entity.auto.ProductOrder;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单mapper
 *
 * @author f
 * @date 2019-03-10
 */
public interface ProductOrderExtMapper {

    /**
     * 保存订单
     *
     * @param record
     * @return
     */
    int saveSelective(ProductOrder record);

    /**
     * 根据用户查询订单
     *
     * @param userName
     * @return
     */
    List<ProductOrder> listOrder(@Param("userName") String userName);

    /**
     * 根据用户,时间查询订单
     *
     * @param userName
     * @return
     */
    List<ProductOrder> listOrderAndDate(@Param("userName") String userName, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("productType") String productType);

    /**
     * 根据id查询订单
     *
     * @param id
     * @return
     */
    ProductOrder getProductOrder(Integer id);
}