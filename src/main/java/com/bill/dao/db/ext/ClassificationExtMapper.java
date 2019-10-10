package com.bill.dao.db.ext;

import com.bill.dao.db.auto.ClassificationMapper;
import com.bill.model.entity.auto.Classification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类mapper
 *
 * @author f
 * @date 2019-10-10
 */
public interface ClassificationExtMapper extends ClassificationMapper {

    /**
     * 查询分类列表
     *
     * @return 分类列表
     */
    List<Classification> listClassification();

    /**
     * 查询分类列表
     *
     * @param ids
     * @return 分类列表
     */
    List<Classification> listClassificationByIds(@Param("ids") List<Integer> ids);

}