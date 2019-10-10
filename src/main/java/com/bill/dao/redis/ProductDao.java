package com.bill.dao.redis;

import com.alibaba.fastjson.JSON;
import com.bill.model.constant.RedisCatchConstant;
import com.bill.model.constant.RedisKeyConstant;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.view.QueryProductVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 产品redis dao
 *
 * @author f
 * @date 2019-04-21
 */
@Component
public class ProductDao {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

    /**
     * 保存产品列表
     *
     * @param pageVmo
     */
    public void saveProductList(PageVO<List<QueryProductVO>> pageVmo) {
        try {
            redisTemplate.opsForValue().set(RedisKeyConstant.PRODUCT_LIST_KEY, JSON.toJSONString(pageVmo), RedisCatchConstant.PRODUCT_LIST_CATCH, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(RedisKeyConstant.PRODUCT_LIST_LONG_KEY, JSON.toJSONString(pageVmo), RedisCatchConstant.PRODUCT_LIST_LONG_CATCH, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("保存产品列表异常：pageVmo=" + JSON.toJSONString(pageVmo), e);
        }
    }

    /**
     * 获取产品列表
     *
     * @return
     */
    public PageVO<List<QueryProductVO>> getProductList() {
        try {
            PageVO<List<QueryProductVO>> result = null;
            Object pageVmo = redisTemplate.opsForValue().get(RedisKeyConstant.PRODUCT_LIST_KEY);
            if (null == pageVmo) {
                pageVmo = redisTemplate.opsForValue().get(RedisKeyConstant.PRODUCT_LIST_LONG_KEY);
                if (null != pageVmo) {
                    result = JSON.parseObject(pageVmo.toString(), PageVO.class);
                    this.saveProductList(result);
                }
            } else {
                result = JSON.parseObject(pageVmo.toString(), PageVO.class);
            }
            return result;
        } catch (Exception e) {
            logger.error("获取产品列表异常", e);
        }
        return null;
    }

    /**
     * 删除产品列表
     */
    public void deleteProductList() {
        try {
            redisTemplate.delete(RedisKeyConstant.PRODUCT_LIST_KEY);
            redisTemplate.delete(RedisKeyConstant.PRODUCT_LIST_LONG_KEY);
        } catch (Exception e) {
            logger.error("删除产品列表异常", e);
        }
    }

}
