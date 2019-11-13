package com.bill.dao.redis;

import com.bill.common.log.LogBackUtils;
import com.bill.model.constant.ScriptConstant;
import com.bill.model.enums.ResultEnum;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * redis工具类
 *
 * @author f
 * @date 2019-08-28
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 分布式加锁
     *
     * @param key
     * @param expire
     * @param sleep
     * @return
     */
    public boolean lock(String key, long expire, long sleep) {
        String value = UUID.randomUUID().toString();
        boolean getLock = this.setNx(key, value, expire);
        if (!getLock && sleep > 0) {
            for (long start = System.currentTimeMillis(); !getLock && System.currentTimeMillis() - start < expire; getLock = this.setNx(key, value, expire)) {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException var10) {
                    return false;
                }
            }
        }
        return getLock;
    }

    /**
     * redis setnx操作
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean setNx(String key, String value, long expire) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        try {
            String result = (String) redisTemplate.execute((RedisConnection connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, value, "NX", "PX", expire);
            });
            return StringUtil.isNotEmpty(result);
        } catch (Exception var8) {
            LogBackUtils.error("RedisUtils-setNx error", var8);
            return false;
        }
    }

    /**
     * redis get操作
     *
     * @param key
     * @return
     */
    public String getNx(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        try {
            String result = (String) redisTemplate.execute((RedisConnection connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.get(key);
            });
            return result;
        } catch (Exception var8) {
            LogBackUtils.error("RedisUtils-getNx error", var8);
            return "";
        }
    }

    /**
     * 释放锁
     *
     * @param key
     * @return
     */
    public boolean releaseLock(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }

        String value = StringUtils.isNotEmpty(this.getNx(key)) ? this.getNx(key) : "";
        if (StringUtils.isNotEmpty(value)) {
            final List<String> keys = new ArrayList();
            keys.add(key);
            final List<String> args = new ArrayList();
            args.add(value);
            Long result = (Long) redisTemplate.execute((RedisConnection redisConnection) -> {
                Object conn = redisConnection.getNativeConnection();
                if (conn instanceof JedisCluster) {
                    return ((JedisCluster) conn).eval(ScriptConstant.REDIS_DEL, keys, args);
                } else if (conn instanceof Jedis) {
                    return ((Jedis) conn).eval(ScriptConstant.REDIS_DEL, keys, args);
                } else {
                    return 0L;
                }
            });
            return result != null && result > 0L;
        } else {
            return true;
        }
    }
}
