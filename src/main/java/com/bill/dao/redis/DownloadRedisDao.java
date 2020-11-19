package com.bill.dao.redis;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.common.util.CheckBeanUtils;
import com.bill.model.constant.RedisCatchConstant;
import com.bill.model.constant.RedisKeyConstant;
import com.bill.model.vo.view.DownloadVmo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 下载redis dao
 *
 * @author f
 * @date 2019-06-03
 */
@Component
public class DownloadRedisDao {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 保存下载链接
     *
     * @param key
     * @param downloadVmo
     */
    public void setDownloadKeyLink(String key, DownloadVmo downloadVmo) {
        try {
            redisUtils.set(RedisKeyConstant.DOWNLOAD_KEY + key, JSON.toJSONString(downloadVmo), RedisCatchConstant.DOWNLOAD_KEY_EXPIRE_SECOND, TimeUnit.SECONDS);
        } catch (Exception e) {
            LogBackUtils.error("保存下载链接redis异常：key=" + key + ",downloadVmo=" + JSON.toJSONString(downloadVmo), e);
        }
    }

    /**
     * 获取下载链接
     *
     * @param key
     * @return
     */
    public DownloadVmo getDownloadKeyLink(String key) {
        try {
            Object result = redisUtils.get(RedisKeyConstant.DOWNLOAD_KEY + key);
            if (null != result && CheckBeanUtils.isNotEmpty(String.valueOf(result))) {
                return JSON.parseObject(String.valueOf(result), DownloadVmo.class);
            }
        } catch (Exception e) {
            LogBackUtils.error("保存下载链接redis异常：key=" + key, e);
        }
        return null;
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean hasDownloadKey(String key) {
        try {
            return redisUtils.hasKey(RedisKeyConstant.DOWNLOAD_KEY + key);
        } catch (Exception e) {
            LogBackUtils.error("是否存在key异常：key=" + key, e);
        }
        return false;
    }
}
