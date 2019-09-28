package com.bill.dao.db.auto;

import com.bill.model.entity.auto.ProductBill;

public interface ProductBillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductBill record);

    int insertSelective(ProductBill record);

    ProductBill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductBill record);

    int updateByPrimaryKey(ProductBill record);
}