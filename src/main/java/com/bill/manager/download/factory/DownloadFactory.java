package com.bill.manager.download.factory;


import com.zkt.datamart.common.download.nullbean.impl.DownloadVmoNull;
import com.zkt.datamart.common.download.vmo.DownloadVmo;

/**
 * 工厂
 *
 * @author f
 * @date 2019-04-08
 */
public class DownloadFactory {

    /**
     * NullDownloadBmo工厂
     *
     * @return
     */
    public static DownloadVmoNull NullDownloadVmoFactory() {
        return new DownloadVmoNull();
    }

    /**
     * 获取下载信息
     *
     * @param downloadVmo
     * @return
     */
    public static DownloadVmo getDownloadVmo(DownloadVmo downloadVmo) {
        return (null == downloadVmo) ? DownloadFactory.NullDownloadVmoFactory() : downloadVmo;
    }
}
