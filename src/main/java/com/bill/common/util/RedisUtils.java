//package com.bill.common.util;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * redis工具类
// *
// * @author f
// * @date 2018-04-23
// */
//@Component
//@ConfigurationProperties(prefix = "redis")
//public final class RedisUtils {
//
//    /**
//     * Redis服务器IP,主 写
//     */
//    private String ADDR;
//
//    /**
//     * Redis的端口号
//     */
//    private int PORT;
//
//    /**
//     * 访问密码
//     */
//    private String AUTH;
//
//    /**
//     * 可用连接实例的最大数目，默认值为8
//     * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)
//     */
//    private int MAXACTIVE;
//
//    /**
//     * 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8
//     */
//    private int MAXIDLE;
//
//    /**
//     * 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
//     */
//    private long MAXWAIT;
//
//    /**
//     * 超时时间
//     */
//    private int TIMEOUT;
//
//    /**
//     * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
//     */
//    private boolean TESTONBORROW;
//
//    /**
//     * 连接池
//     */
//    private volatile JedisPool pool = null;
//
//    /**
//     * 构建redis连接池
//     *
//     * @return JedisPool
//     */
//    public JedisPool getPool() {
//        try {
//            if (pool == null) {
//                synchronized (RedisUtils.class) {
//                    if (pool == null) {
//                        JedisPoolConfig config = new JedisPoolConfig();
//                        config.setMaxActive(MAXACTIVE);
//                        config.setMaxIdle(MAXIDLE);
//                        config.setMaxWait(MAXWAIT);
//                        config.setTestOnBorrow(TESTONBORROW);
//                        pool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        return pool;
//    }
//
//    /**
//     * 获取Jedis实例
//     *
//     * @return
//     */
//    public Jedis getJedis() {
//        Jedis resource = null;
//        try {
//            if (this.getPool() != null) {
//                resource = this.getPool().getResource();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            //释放redis对象
//            pool.returnBrokenResource(resource);
//        }
//        return resource;
//    }
//
//    /**
//     * 返还到连接池,释放redis对象
//     *
//     * @param jedis
//     */
//    public void returnResource(Jedis jedis) {
//        if (jedis != null) {
//            this.getPool().returnResource(jedis);
//        }
//    }
//
//    public String getADDR() {
//        return ADDR;
//    }
//
//    public void setADDR(String ADDR) {
//        this.ADDR = ADDR;
//    }
//
//    public int getPORT() {
//        return PORT;
//    }
//
//    public void setPORT(int PORT) {
//        this.PORT = PORT;
//    }
//
//    public String getAUTH() {
//        return AUTH;
//    }
//
//    public void setAUTH(String AUTH) {
//        this.AUTH = AUTH;
//    }
//
//    public int getTIMEOUT() {
//        return TIMEOUT;
//    }
//
//    public void setTIMEOUT(int TIMEOUT) {
//        this.TIMEOUT = TIMEOUT;
//    }
//
//    public int getMAXACTIVE() {
//        return MAXACTIVE;
//    }
//
//    public void setMAXACTIVE(int MAXACTIVE) {
//        this.MAXACTIVE = MAXACTIVE;
//    }
//
//    public int getMAXIDLE() {
//        return MAXIDLE;
//    }
//
//    public void setMAXIDLE(int MAXIDLE) {
//        this.MAXIDLE = MAXIDLE;
//    }
//
//    public long getMAXWAIT() {
//        return MAXWAIT;
//    }
//
//    public void setMAXWAIT(long MAXWAIT) {
//        this.MAXWAIT = MAXWAIT;
//    }
//
//    public boolean isTESTONBORROW() {
//        return TESTONBORROW;
//    }
//
//    public void setTESTONBORROW(boolean TESTONBORROW) {
//        this.TESTONBORROW = TESTONBORROW;
//    }
//
//}
