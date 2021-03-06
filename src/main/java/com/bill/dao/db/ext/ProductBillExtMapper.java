package com.bill.dao.db.ext;

import com.bill.dao.db.auto.ProductBillMapper;
import com.bill.model.po.auto.ProductBill;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品账单mapper
 *
 * @author f
 * @date 2019-03-10
 */
public interface ProductBillExtMapper extends ProductBillMapper {

    /**
     * 根据用户查询账单
     *
     * @param memberId
     * @return
     */
    List<ProductBill> listProductBill(@Param("memberId") Integer memberId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("classificationId") Integer classificationId);

    /**
     * 根据用户,时间查询账单
     *
     * @param memberId
     * @return
     */
    List<ProductBill> listProductBillAndDate(@Param("memberId") Integer memberId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

}