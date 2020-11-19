package com.bill.service;

import com.bill.manager.adapter.DownloadAdapter;
import com.bill.model.vo.param.DownloadParamVmo;
import com.bill.model.vo.view.DownloadVmo;

/**
 * 下载service
 *
 * @author f
 * @date 2019-04-03
 */
public interface DownloadService {

    /**
     * 执行sql
     *
     * @param downloadAdapter
     * @return
     */
    DownloadAdapter listDownloadData(DownloadAdapter downloadAdapter);

    /**
     * 下载excel
     *
     * @param downloadParamVmo
     * @return
     */
    String downloadExcel(DownloadParamVmo downloadParamVmo);

    /**
     * 获取下载结果
     *
     * @param key
     * @return
     */
    DownloadVmo getDownloadKeyLink(String key);

}
