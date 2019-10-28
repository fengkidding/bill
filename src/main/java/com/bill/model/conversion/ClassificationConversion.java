package com.bill.model.conversion;

import com.bill.model.po.auto.Classification;
import com.bill.model.vo.param.ClassificationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商品数据转换
 *
 * @author f
 * @date 2019-10-10
 */
@Mapper(componentModel = "spring")
public interface ClassificationConversion {

    ClassificationConversion CLASSIFICATION_CONVERSION = Mappers.getMapper(ClassificationConversion.class);

    /**
     * 商品分类数据
     *
     * @param classificationVo
     * @return
     */
    Classification voToDo(ClassificationVO classificationVo);

    /**
     * 商品分类数据
     *
     * @param classification
     * @return
     */
    ClassificationVO doToVo(Classification classification);

    /**
     * 商品分类数据
     *
     * @param classifications
     * @return
     */
    List<ClassificationVO> doToVo(List<Classification> classifications);
}
