package com.bill.model.constant;

/**
 * 脚本常量
 *
 * @author f
 * @date 2019-08-28
 */
public class ScriptConstant {

    /**
     * redis 删除脚本
     */
    public static final String REDIS_DEL = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private ScriptConstant() {
    }
}
