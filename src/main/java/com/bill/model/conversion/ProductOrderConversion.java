package com.bill.model.conversion;

import com.bill.model.entity.auto.ProductOrder;
import com.bill.model.vo.view.QueryOrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * 商品订单数据
 *
 * @author f
 * @date 2018-07-11
 */
@Mapper(componentModel = "spring")
public interface ProductOrderConversion {

    /**
     * 常量
     */
    ProductOrderConversion PRODUCT_ORDER_CONVERSION = Mappers.getMapper(ProductOrderConversion.class);

    /**
     * 保存商品订单数据
     *
     * @param productOrder
     */
    QueryOrderVO entityToVmo(ProductOrder productOrder);

    /**
     * 保存商品订单数据
     *
     * @param productOrder
     */
    List<QueryOrderVO> entityToVmo(List<ProductOrder> productOrder);

}
