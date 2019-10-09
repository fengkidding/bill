package com.bill.dao.db.auto;

import com.bill.model.entity.auto.Classification;

public interface ClassificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classification record);

    int insertSelective(Classification record);

    Classification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classification record);

    int updateByPrimaryKey(Classification record);
}