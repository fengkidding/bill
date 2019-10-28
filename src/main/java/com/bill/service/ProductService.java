package com.bill.service;

import com.bill.model.po.auto.Product;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.view.QueryProductVO;

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
}
