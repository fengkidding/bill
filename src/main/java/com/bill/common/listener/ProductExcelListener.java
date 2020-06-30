package com.bill.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.common.util.SpringUtils;
import com.bill.model.conversion.ProductConversion;
import com.bill.model.dto.ProductSaveDto;
import com.bill.model.po.auto.Product;
import com.bill.service.ProductService;

import java.util.Optional;

/**
 * 读取产品excel
 *
 * @author f
 * @date 2020-06-30
 */
public class ProductExcelListener extends AnalysisEventListener<ProductSaveDto> {

    /**
     * 一行一行读取excel内容
     *
     * @param productSaveDto
     * @param analysisContext
     */
    @Override
    public void invoke(ProductSaveDto productSaveDto, AnalysisContext analysisContext) {
        LogBackUtils.info("ProductExcelListener.invoke productSaveDto=" + JSON.toJSONString(productSaveDto));
        Product product = ProductConversion.PRODUCT_CONVERSION.dtoToPo(productSaveDto);
        if (Optional.ofNullable(product).isPresent()) {
            SpringUtils.getApplicationContext().getBean(ProductService.class).saveProduct(product);
        }
    }

    /**
     * 读取完成之后
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LogBackUtils.info("ProductExcelListener.doAfterAllAnalysed end");
    }
}
