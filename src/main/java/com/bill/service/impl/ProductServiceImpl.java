package com.bill.service.impl;


import com.bill.common.util.RemainingSumUtils;
import com.bill.dao.db.ext.ProductExtMapper;
import com.bill.dao.redis.ProductDao;
import com.bill.dao.redis.RedisUtils;
import com.bill.model.constant.RedisCatchConstant;
import com.bill.model.constant.RedisKeyConstant;
import com.bill.model.conversion.ProductConversion;
import com.bill.model.entity.auto.Product;
import com.bill.model.vmo.common.PageVmo;
import com.bill.model.vmo.view.QueryProduct;
import com.bill.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 商品service
 *
 * @author f
 * @date 2019-03-10
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductExtMapper productExtMapper;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 创建商品
     *
     * @param product 商品实体
     */
    @Override
    public void saveProduct(Product product) {
        productExtMapper.insertSelective(product);
    }

    /**
     * 更新商品
     *
     * @param product 商品实体
     */
    @Override
    public void updateProduct(Product product) {
        productExtMapper.updateByPrimaryKeySelective(product);
        productDao.deleteProductList();
    }

    /**
     * 根据id获取商品信息
     *
     * @param id 商品id
     * @return
     */
    @Override
    public Product getProduct(Integer id) {
        return productExtMapper.selectByPrimaryKey(id);
    }

    /**
     * 售出商品，更新库存已售
     *
     * @param id
     * @param total
     */
    @Override
    public void soldProduct(Integer id, Integer total) {
        productExtMapper.soldProduct(id, total);
    }

    /**
     * 分页查询商品信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageVmo<List<QueryProduct>> listProduct(Integer pageNum, Integer pageSize) {
        PageVmo<List<QueryProduct>> pageVmo = productDao.getProductList();
        if (null == pageVmo) {
            try {
                boolean getLock = redisUtils.lock(RedisKeyConstant.PRODUCT_LIST_LOCK_KEY, RedisCatchConstant.PRODUCT_LIST_LOCK_CATCH, RedisCatchConstant.PRODUCT_LIST_LOCK_SLEEP);
                if (getLock) {
                    pageVmo = productDao.getProductList();
                    if (null == pageVmo) {
                        pageVmo = new PageVmo<>();
                        Page page = PageHelper.startPage(pageNum, pageSize);
                        List<Product> list = productExtMapper.listProduct();
                        pageVmo.setTotal(page.getTotal());
                        if (!CollectionUtils.isEmpty(list)) {
                            List<QueryProduct> vmoList = ProductConversion.PRODUCT_CONVERSION.entityToVmo(list);
                            for (int i = 0; i < vmoList.size(); i++) {
                                vmoList.get(i).setPrice(RemainingSumUtils.getYuan(list.get(i).getPrice()));
                            }
                            pageVmo.setData(vmoList);

                            productDao.saveProductList(pageVmo);
                        }
                    }
                }
            } catch (Exception e) {
                throw e;
            } finally {
                redisUtils.releaseLock(RedisKeyConstant.PRODUCT_LIST_LOCK_KEY);
            }
        }

        return pageVmo;
    }
}
