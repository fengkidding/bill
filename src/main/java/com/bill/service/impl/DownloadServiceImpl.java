package com.bill.service.impl;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.common.util.CheckBeanUtils;
import com.bill.common.util.DownloadUtils;
import com.bill.dao.redis.DownloadRedisDao;
import com.bill.manager.adapter.DownloadAdapter;
import com.bill.manager.factory.DownloadAdapterFactory;
import com.bill.model.constant.FileConstant;
import com.bill.model.enums.ResultEnum;
import com.bill.model.exception.ServiceException;
import com.bill.model.vo.param.DownloadParamVmo;
import com.bill.model.vo.view.DownloadVmo;
import com.bill.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 下载service
 *
 * @author f
 * @date 2019-04-03
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private DownloadRedisDao downloadRedisDao;

    @Value("${url.link}")
    private String linkUrl;

    /**
     * 执行sql
     *
     * @param downloadAdapter
     * @return
     */
    @Override
    public DownloadAdapter listDownloadData(DownloadAdapter downloadAdapter) {
        List<Map<String, Object>> list = downloadAdapter.excuteSql();
        downloadAdapter.setItems(list);
        return downloadAdapter;
    }

    /**
     * 下载excel
     *
     * @param downloadParamVmo
     * @return
     */
    @Override
    public String downloadExcel(DownloadParamVmo downloadParamVmo) {
        String key = FileConstant.EXCEL_NAME + downloadParamVmo.getDownloadType() + Objects.hash(JSON.toJSONString(downloadParamVmo.getDownloadKeyParamVmos()));
        if (CheckBeanUtils.checkNotNullZero(downloadParamVmo.getIsMandatoryUpdate()) || !downloadRedisDao.hasDownloadKey(key)) {
            //下载适配器
            DownloadAdapter downloadAdapter = DownloadAdapterFactory.getDownloadAdapter(downloadParamVmo);
            if (null == downloadAdapter) {
                throw new ServiceException(ResultEnum.ADAPTER_PARAM_ERROR);
            }

            DownloadVmo downloadVmo = new DownloadVmo();
            downloadVmo.setCompleteStatus(1);
            downloadVmo.setLocalFileName(FileConstant.EXCEL_NAME + UUID.randomUUID().toString());
            downloadVmo.setDownloadProgress(BigDecimal.ZERO);
            downloadRedisDao.setDownloadKeyLink(key, downloadVmo);

            //生成excel
            String filePath = downloadVmo.getLocalFileName() + FileConstant.XLSX;
            try {
                downloadAdapter = this.listDownloadData(downloadAdapter);
                if (CollectionUtils.isEmpty(downloadAdapter.getItems())) {
                    downloadVmo.setCompleteStatus(0);
                } else {
                    DownloadUtils.createExcel(filePath, downloadAdapter.getItems(), downloadAdapter.getHeader());

                    // 上传文件(正常要上传到文件服务器)，现在用本地代替
                    downloadVmo.setLink(linkUrl + filePath);
                    downloadVmo.setCompleteStatus(2);
                }
            } catch (Exception e) {
                LogBackUtils.error("downloadExcel异常", e);
                downloadVmo.setCompleteStatus(3);
            }
            downloadRedisDao.setDownloadKeyLink(key, downloadVmo);
        }
        return key;
    }

    /**
     * 获取下载结果
     *
     * @param key
     * @return
     */
    @Override
    public DownloadVmo getDownloadKeyLink(String key) {
        return downloadRedisDao.getDownloadKeyLink(key);
    }

}
