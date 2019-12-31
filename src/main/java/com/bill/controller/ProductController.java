package com.bill.controller;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.common.util.AuthContextUtils;
import com.bill.common.util.CheckBeanUtils;
import com.bill.common.util.ComputeUtils;
import com.bill.model.conversion.ProductConversion;
import com.bill.model.po.auto.Product;
import com.bill.model.vo.common.PageParamVO;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.common.ResultVO;
import com.bill.model.vo.param.ProductSaveParamVO;
import com.bill.model.vo.view.QueryProductVO;
import com.bill.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private ProductService productService;

    /**
     * 保存商品
     *
     * @param productSaveParamVmo
     * @return
     */
    @ApiOperation(value = "保存商品")
    @PostMapping(value = "/save-product")
    public ResultVO saveProduct(@RequestBody @Valid ProductSaveParamVO productSaveParamVmo, HttpServletRequest request) {
        LogBackUtils.info("保存商品: productSaveParamVmo=" + JSON.toJSONString(productSaveParamVmo));
        Product product = new Product();
        ProductConversion.PRODUCT_CONVERSION.vmoToEntity(productSaveParamVmo, product);
        product.setPrice(ComputeUtils.getFen(productSaveParamVmo.getPrice()));

        Integer memberId = AuthContextUtils.getLoginMemberId();
        product.setMemberId(memberId);
        if (CheckBeanUtils.checkNotNullZero(productSaveParamVmo.getId())) {
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
    @GetMapping(value = "/list-product")
    public ResultVO<PageVO<List<QueryProductVO>>> listProduct(@Valid PageParamVO pageParamVmo) throws Exception {
        PageVO<List<QueryProductVO>> pageVmo = productService.listProduct(pageParamVmo.getPageNum(), pageParamVmo.getPageSize());
        return super.resultSuccess(pageVmo);
    }

    /**
     * 商品销量排行榜
     *
     * @return
     */
    @ApiOperation(value = "商品销量排行榜")
    @GetMapping(value = "/ranking-product")
    public ResultVO<List<QueryProductVO>> rankingProduct() {
        return super.resultSuccess(productService.rankingProduct());
    }
}
