package com.bill.manager.factory;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.manager.adapter.DownloadAdapter;
import com.bill.model.enums.DownloadEnums;
import com.bill.model.vo.param.DownloadParamVmo;

import java.lang.reflect.Constructor;

/**
 * 下载策略 环境选择工程
 *
 * @author f
 * @date 2019-07-05
 */
public class DownloadAdapterFactory {

    /**
     * 获取DownloadAdapter对象
     *
     * @param downloadParamVmo
     * @return
     */
    public static DownloadAdapter getDownloadAdapter(DownloadParamVmo downloadParamVmo) {
        DownloadAdapter downloadAdapter = null;
        DownloadEnums downloadEnums = DownloadEnums.valueOf(downloadParamVmo.getDownloadType());
        try {
            Class adapter = Class.forName(downloadEnums.getClassName());
            try {
                Constructor constructor = adapter.getConstructor(new Class[]{DownloadParamVmo.class});
                Object o = constructor.newInstance(new Object[]{downloadParamVmo});
                downloadAdapter = (DownloadAdapter) o;
            } catch (Exception e) {
                LogBackUtils.error("DownloadAdapterFactory-适配器反射异常：downloadParamVmo=" + JSON.toJSONString(downloadParamVmo), e);
            }
        } catch (ClassNotFoundException e) {
            LogBackUtils.error("DownloadAdapterFactory-找不到对应的适配器：downloadParamVmo=" + JSON.toJSONString(downloadParamVmo), e);
        }
        return downloadAdapter;
    }
}
