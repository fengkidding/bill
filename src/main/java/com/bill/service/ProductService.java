package com.bill.service;

import com.bill.model.po.auto.Product;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.SaveProductForExcelVO;
import com.bill.model.vo.view.QueryProductVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品service
 *
 * @author f
 * @date 2019-03-10
 */
public interface ProductService {

    /**
     * 创建商品
     *
     * @param product 商品实体
     */
    void saveProduct(Product product);

    /**
     * 更新商品
     *
     * @param product 商品实体
     */
    void updateProduct(Product product);

    /**
     * 根据id获取商品信息
     *
     * @param id 商品id
     * @return
     */
    Product getProduct(Integer id);

    /**
     * 售出商品，更新库存已售
     *
     * @param id
     * @param total
     */
    void soldProduct(Integer id, Integer total);

    /**
     * 分页查询商品信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageVO<List<QueryProductVO>> listProduct(Integer pageNum, Integer pageSize);

    /**
     * 商品销量排行榜
     *
     * @return
     */
    List<QueryProductVO> rankingProduct();

    /**
     * 设置产品过期
     *
     * @param localDateTime
     * @return
     */
    Integer expiredProduct(LocalDateTime localDateTime);

    /**
     * 获取产品详情
     *
     * @param id
     * @return
     */
    QueryProductVO getQueryProductVO(Integer id);

    /**
     * 根据excel导入产品
     *
     * @param saveProductForExcelVO
     */
    void saveProductForExcel(SaveProductForExcelVO saveProductForExcelVO);
}
