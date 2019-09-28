package com.bill.model.conversion;

import com.bill.model.entity.auto.Product;
import com.bill.model.vmo.param.ProductSaveParamVmo;
import com.bill.model.vmo.view.QueryProduct;
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
    void vmoToEntity(ProductSaveParamVmo productSaveParamVmo, @MappingTarget Product product);

    /**
     * 查询商品数据
     *
     * @param product
     * @return
     */
    QueryProduct entityToVmo(Product product);

    /**
     * 查询商品数据
     *
     * @param products
     * @return
     */
    List<QueryProduct> entityToVmo(List<Product> products);
}
