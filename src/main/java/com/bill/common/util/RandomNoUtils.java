package com.bill.common.util;

import org.apache.commons.lang.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 随机生成不重复数字
 *
 * @author f
 * @date 2019-08-29
 */
public class RandomNoUtils {

    /**
     * 时间起始标记点，作为基准，一般取系统的最近时间（一旦确定不能变动）
     */
    private final static long twepoch = System.currentTimeMillis();

    /**
     * 机器标识位数
     */
    private final static long workerIdBits = 5L;

    /**
     * 数据中心标识位数
     */
    private final static long datacenterIdBits = 5L;

    /**
     * 机器ID最大值
     */
    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 数据中心ID最大值
     */
    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 毫秒内自增位
     */
    private final static long sequenceBits = 12L;

    /**
     * 机器ID偏左移12位
     */
    private final static long workerIdShift = sequenceBits;

    /**
     * 数据中心ID左移17位
     */
    private final static long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间毫秒左移22位
     */
    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 毫秒内自增位最大值
     */
    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 上次生产id时间戳
     */
    private static long lastTimestamp = -1L;

    /**
     * 0，并发控制
     */
    private static long sequence = 0L;

    /**
     * 数据标识id部分
     */
    private static final long datacenterId = RandomNoUtils.getDatacenterId(maxDatacenterId);

    /**
     * 工作机器ID
     */
    private static final long workerId = RandomNoUtils.getMaxWorkerId(datacenterId, maxWorkerId);

    /**
     * 获取下一个ID
     *
     * @return id
     */
    public synchronized static long nextId() {
        long timestamp = System.currentTimeMillis();
        System.out.println("当前时间" + timestamp + ",上一次生成时间" + lastTimestamp);
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = RandomNoUtils.tilNextMillis(lastTimestamp);
            }
            System.out.println("并发控制" + sequence);
        } else {
            System.out.println("重制并发参数" + sequence);
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        // ID偏移组合生成最终的ID，并返回ID
        long nextId = ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;

        return nextId;
    }

    /**
     * 当前毫秒内计数满了，则等待下一豪秒
     *
     * @param lastTimestamp
     * @return
     */
    private static long tilNextMillis(final long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        System.out.println("毫秒内计数" + lastTimestamp);
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        System.out.println("毫秒内计数等待完成" + lastTimestamp);
        return timestamp;
    }

    /**
     * 获取 maxWorkerId
     *
     * @return id
     */
    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (StringUtils.isNotEmpty(name)) {
            //  GET jvmPid
            mpid.append(name.split("@")[0]);
        }
        System.out.println("getMaxWorkerId" + (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1));
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * 数据标识id部分
     *
     * @return id
     */
    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            ip = InetAddress.getByName("192.168.4.102");
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null || null == network.getHardwareAddress()) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDatacenterId + 1);
            }
        } catch (Exception e) {
            System.out.println("getDatacenterId error: " + e.getMessage());
        }
        return id;
    }

    public static void main(String[] args) {
        List<Long> array = new LinkedList<>();
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Long id = RandomNoUtils.nextId();
            System.out.println("id=" + id);
            array.add(id);
            set.add(id);
        }
        System.out.println("array=" + array.size());
        System.out.println("set=" + set.size());
    }
}
