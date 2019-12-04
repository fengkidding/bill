package com.bill.dao.redis;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.model.constant.RedisCatchConstant;
import com.bill.model.constant.RedisKeyConstant;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.view.QueryProductVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RedisUtils redisUtils;

    /**
     * 保存产品列表
     *
     * @param pageVmo
     */
    public void saveProductList(PageVO<List<QueryProductVO>> pageVmo) {
        try {
            redisUtils.set(RedisKeyConstant.PRODUCT_LIST_KEY, JSON.toJSONString(pageVmo), RedisCatchConstant.PRODUCT_LIST_CATCH, TimeUnit.SECONDS);
            redisUtils.set(RedisKeyConstant.PRODUCT_LIST_LONG_KEY, JSON.toJSONString(pageVmo), RedisCatchConstant.PRODUCT_LIST_LONG_CATCH, TimeUnit.SECONDS);
        } catch (Exception e) {
            LogBackUtils.error("保存产品列表异常：pageVmo=" + JSON.toJSONString(pageVmo), e);
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
            Object pageVmo = redisUtils.get(RedisKeyConstant.PRODUCT_LIST_KEY);
            if (null == pageVmo) {
                pageVmo = redisUtils.get(RedisKeyConstant.PRODUCT_LIST_LONG_KEY);
                if (null != pageVmo) {
                    result = JSON.parseObject(pageVmo.toString(), PageVO.class);
                    this.saveProductList(result);
                }
            } else {
                result = JSON.parseObject(pageVmo.toString(), PageVO.class);
            }
            return result;
        } catch (Exception e) {
            LogBackUtils.error("获取产品列表异常", e);
        }
        return null;
    }

    /**
     * 删除产品列表
     */
    public void deleteProductList() {
        try {
            redisUtils.delete(RedisKeyConstant.PRODUCT_LIST_KEY);
            redisUtils.delete(RedisKeyConstant.PRODUCT_LIST_LONG_KEY);
        } catch (Exception e) {
            LogBackUtils.error("删除产品列表异常", e);
        }
    }

}
