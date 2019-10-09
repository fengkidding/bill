package com.bill.controller;

import com.alibaba.fastjson.JSON;
import com.bill.common.util.CheckBeanUtil;
import com.bill.common.util.RemainingSumUtils;
import com.bill.model.conversion.ProductConversion;
import com.bill.model.entity.auto.Product;
import com.bill.model.vmo.common.PageParamVmo;
import com.bill.model.vmo.common.PageVmo;
import com.bill.model.vmo.common.ResultVmo;
import com.bill.model.vmo.param.ProductSaveParamVmo;
import com.bill.model.vmo.view.QueryProduct;
import com.bill.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 商品接口
 *
 * @author f
 * @date 2018-04-22
 */
@Api(description = "商品接口")
@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * 保存商品
     *
     * @param productSaveParamVmo
     * @return
     */
    @ApiOperation(value = "保存商品")
    @PostMapping(value = "/save_product")
    public ResultVmo saveProduct(@RequestBody @Valid ProductSaveParamVmo productSaveParamVmo, HttpServletRequest request) {
        logger.info("更新用户余额: traceId=" + request.getAttribute("traceId") + ",productSaveParamVmo=" + JSON.toJSONString(productSaveParamVmo));
        Product product = new Product();
        ProductConversion.PRODUCT_CONVERSION.vmoToEntity(productSaveParamVmo, product);
        product.setPrice(RemainingSumUtils.getFen(productSaveParamVmo.getPrice()));
        if (CheckBeanUtil.checkNotNullZero(productSaveParamVmo.getId())) {
            productService.updateProduct(product);
        } else {
            productService.saveProduct(product);
        }
        return super.resultSuccess();
    }

    /**
     * 分页查询商品列表
     *
     * @param pageParamVmo
     * @return
     */
    @ApiOperation(value = "分页查询商品列表")
    @GetMapping(value = "/list_product")
    public ResultVmo<PageVmo<List<QueryProduct>>> listProduct(@Valid PageParamVmo pageParamVmo) {
        PageVmo<List<QueryProduct>> pageVmo = productService.listProduct(pageParamVmo.getPageNum(), pageParamVmo.getPageSize());
        return super.resultSuccess(pageVmo);
    }

}
