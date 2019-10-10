package com.bill.service.impl;

import com.bill.dao.db.ext.ClassificationExtMapper;
import com.bill.model.conversion.ClassificationConversion;
import com.bill.model.entity.auto.Classification;
import com.bill.model.vo.common.PageParamVO;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.ClassificationVO;
import com.bill.service.ClassificationSerrvice;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 商品分类service
 *
 * @author f
 * @date 2019-10-10
 */
@Service
public class ClassificationSerrviceImpl implements ClassificationSerrvice {

    @Autowired
    private ClassificationExtMapper classificationExtMapper;

    /**
     * 保存商品分类
     *
     * @param classification 分类实体
     */
    @Override
    public void saveClassification(Classification classification) {
        if (null != classification) {
            classificationExtMapper.insertSelective(classification);
        }
    }

    /**
     * 分页查询分类
     *
     * @param pageParamVmo
     * @return 分类列表
     */
    @Override
    public PageVO<List<ClassificationVO>> listClassification(PageParamVO pageParamVmo) {
        PageVO<List<ClassificationVO>> pageVmo = new PageVO<>();
        Page page = PageHelper.startPage(pageParamVmo.getPageNum(), pageParamVmo.getPageSize());
        List<Classification> list = classificationExtMapper.listClassification();
        pageVmo.setTotal(page.getTotal());
        if (!CollectionUtils.isEmpty(list)) {
            pageVmo.setData(ClassificationConversion.CLASSIFICATION_CONVERSION.doToVo(list));
        }
        return pageVmo;
    }

    /**
     * 根据id集合查询分类列表
     *
     * @param ids id集合
     * @return
     */
    @Override
    public List<Classification> listClassificationByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        } else {
            return classificationExtMapper.listClassificationByIds(ids);
        }
    }
}
