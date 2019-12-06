package com.bill.dao.redis;

import com.bill.common.log.LogBackUtils;
import com.bill.model.constant.ScriptConstant;
import com.bill.model.enums.ResultEnum;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Value("${spring.application.name}")
    private String appName;

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
     * redis setNx操作
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
                return commands.set(appName + key, value, "NX", "PX", expire);
            });
            return StringUtil.isNotEmpty(result);
        } catch (Exception var8) {
            LogBackUtils.error("RedisUtils-setNx error", var8);
            return false;
        }
    }

    /**
     * redis getNx操作
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
                return commands.get(appName + key);
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
            keys.add(appName + key);
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

    /**
     * set
     *
     * @param key
     * @param value
     */
    @Deprecated
    public void set(String key, Object value) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        redisTemplate.opsForValue().set(appName + key, value);
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     */
    public void set(String key, Object value, long expire, TimeUnit timeUnit) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        redisTemplate.opsForValue().set(appName + key, value, expire, timeUnit);
    }

    /**
     * get
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        return redisTemplate.opsForValue().get(appName + key);
    }

    /**
     * increment操作，如果key不存在会自动创建
     *
     * @param key
     * @param value
     * @return 操作之后保存的值
     */
    public Long increment(String key, long value) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        return redisTemplate.opsForValue().increment(appName + key, value);
    }

    /**
     * increment操作，如果key不存在会自动创建
     *
     * @param key
     * @param value
     * @return 操作之后保存的值
     */
    public Double increment(String key, double value) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        return redisTemplate.opsForValue().increment(appName + key, value);
    }

    /**
     * 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以毫秒为单位，返回 key 的剩余生存时间。
     *
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        return redisTemplate.getExpire(appName + key);
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     */
    public void setExpire(String key, long timeout, TimeUnit unit) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        redisTemplate.expire(appName + key, timeout, unit);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void delete(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        redisTemplate.delete(appName + key);
    }

    /**
     * 是否含有key
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException(ResultEnum.KEY_NONE.getMsg());
        }
        return redisTemplate.hasKey(appName + key);
    }
}
