package com.bill.service;

import com.bill.model.entity.auto.Classification;
import com.bill.model.vo.common.PageParamVO;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.ClassificationVO;

import java.util.List;

/**
 * 商品分类service
 *
 * @author f
 * @date 2019-10-10
 */
public interface ClassificationSerrvice {

    /**
     * 保存商品分类
     *
     * @param classification 分类实体
     */
    void saveClassification(Classification classification);

    /**
     * 分页查询分类
     *
     * @param pageParamVmo
     * @return 分类列表
     */
    PageVO<List<ClassificationVO>> listClassification(PageParamVO pageParamVmo);

    /**
     * 根据id集合查询分类列表
     *
     * @param ids id集合
     * @return
     */
    List<Classification> listClassificationByIds(List<Integer> ids);
}
