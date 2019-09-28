package com.bill.common.util;

import java.io.*;

/**
 * 序列化
 *
 * @author f
 * @date 2018-04-23
 */
public class SerializableUtils {

    /**
     * 对象序列化
     *
     * @param object
     * @return
     */
    public static byte[] openSerializable(Object object) throws IOException {
        byte[] bytes = new byte[]{};
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
        return bytes;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object backSerializable(byte[] bytes) throws IOException {
        Object object = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return object;
    }

}
