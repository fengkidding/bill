package com.bill.model.conversion;

import com.bill.model.po.auto.Product;
import com.bill.model.vo.param.ProductSaveParamVO;
import com.bill.model.vo.view.QueryProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * 商品数据
 *
 * @author f
 * @date 2018-07-11
 */
@Mapper(componentModel = "spring")
public interface ProductConversion {

    /**
     * 常量
     */
    ProductConversion PRODUCT_CONVERSION = Mappers.getMapper(ProductConversion.class);

    /**
     * 保存商品数据
     *
     * @param product
     */
    void vmoToEntity(ProductSaveParamVO productSaveParamVmo, @MappingTarget Product product);

    /**
     * 查询商品数据
     *
     * @param product
     * @return
     */
    QueryProductVO entityToVmo(Product product);

    /**
     * 查询商品数据
     *
     * @param products
     * @return
     */
    List<QueryProductVO> entityToVmo(List<Product> products);
}
