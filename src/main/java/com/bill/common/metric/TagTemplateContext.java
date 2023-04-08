package com.bill.common.metric;

import com.bill.common.log.LogBackUtils;
import org.apache.commons.lang.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * 根据tagTemplate获取对应的实现类
 */
public class TagTemplateContext {

    /**
     * BaseTagTemplate所有实现类
     */
    private static Map<String, BaseTagTemplate> templateMap = TagTemplateContext.findSubInstance(
            BaseTagTemplate.class, "com.bill.common");

    /**
     * 初始化加载类
     */
    public static void init() {
        LogBackUtils.info("TagTemplateContext init-------");
    }

    /**
     * 根据tagTemplate获取对应的实现类
     *
     * @param tagTemplate
     * @return
     */
    public static BaseTagTemplate getTagTemplate(String tagTemplate) {
        BaseTagTemplate baseTagTemplate = templateMap.get(tagTemplate);
        if (null != baseTagTemplate) {
            return baseTagTemplate;
        }
        return null;
    }

    /**
     * 获取所有实现类
     *
     * @param tClass
     * @param pack
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> findSubInstance(Class<T> tClass, String pack) {
        Map<String, T> impls = new HashMap();
        Set<Class<? extends T>> classes = getSubTypesOf(tClass, pack);
        Iterator var4 = classes.iterator();

        while (var4.hasNext()) {
            Class clazz = (Class) var4.next();

            try {
                T instance = (T) clazz.newInstance();
                impls.put(StringUtils.substringAfterLast(clazz.getName(), "."), instance);
            } catch (Exception var7) {
                throw new IllegalArgumentException(var7);
            }
        }

        return impls;
    }

    public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type, String packageName) {
        Reflections reflections = new Reflections(packageName,
                new Scanner[]{new SubTypesScanner(true)});
        return reflections.getSubTypesOf(type);
    }

    private TagTemplateContext() {

    }
}
