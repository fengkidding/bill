package com.bill.manager.adapter;

import com.bill.model.vo.param.DownloadParamVmo;

import java.util.List;
import java.util.Map;

/**
 * 下载适配器
 *
 * @author f
 * @date 2019-04-03
 */
public abstract class DownloadAdapter {

    /**
     * 文件标题名称
     */
    protected List<String> headers;

    /**
     * 文件数据
     */
    protected List<List<String>> datas;

    /**
     * sql参数
     */
    protected DownloadParamVmo downloadParamVmo;

    /**
     * 构造方法
     *
     * @param downloadParamVmo
     */
    public DownloadAdapter(DownloadParamVmo downloadParamVmo) {
        this.downloadParamVmo = downloadParamVmo;
        this.setHeader();
    }

    /**
     * 设置文件头
     */
    public abstract void setHeader();

    /**
     * 获取文件头
     *
     * @return
     */
    public List<String> getHeader() {
        return headers;
    }

    /**
     * 设置数据
     *
     * @param list
     */
    public abstract void setItems(List<Map<String, Object>> list);

    /**
     * 获取数据
     *
     * @return
     */
    public List<List<String>> getItems() {
        return datas;
    }

    /**
     * 只调用dao的方法
     */
    public abstract List<Map<String, Object>> excuteSql();

    public DownloadParamVmo getDownloadParamVmo() {
        return downloadParamVmo;
    }
}
