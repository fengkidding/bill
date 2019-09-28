package com.bill.dao.db.ext;

import com.bill.dao.db.auto.ProductMapper;
import com.bill.model.entity.auto.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品mapper
 *
 * @author f
 * @date 2019-03-10
 */
public interface ProductExtMapper extends ProductMapper {

    /**
     * 售出商品，更新库存已售
     *
     * @param id
     * @param total
     * @return
     */
    int soldProduct(@Param("id") Integer id, @Param("total") Integer total);

    /**
     * 查询商品
     *
     * @return
     */
    List<Product> listProduct();
}