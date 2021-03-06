package com.bill.model.conversion;

import com.bill.model.dto.ProductBillDto;
import com.bill.model.po.auto.ProductBill;
import com.bill.model.vo.view.QueryProductBillVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * 商品账单数据
 *
 * @author f
 * @date 2018-07-11
 */
@Mapper(componentModel = "spring")
public interface ProductBillConversion {

    /**
     * 常量
     */
    ProductBillConversion PRODUCT_BILL_CONVERSION = Mappers.getMapper(ProductBillConversion.class);

    /**
     * 查询账单数据
     *
     * @param productBill
     * @return
     */
    QueryProductBillVO entityToVmo(ProductBill productBill);

    /**
     * 查询账单数据
     *
     * @param productBills
     * @return
     */
    List<QueryProductBillVO> entityToVmo(List<ProductBill> productBills);

    /**
     * 账单dto
     *
     * @param queryProductBillVO
     * @return
     */
    ProductBillDto voToDto(QueryProductBillVO queryProductBillVO);

    /**
     * 账单dto
     *
     * @param queryProductBillVOs
     * @return
     */
    List<ProductBillDto> voToDto(List<QueryProductBillVO> queryProductBillVOs);

}
