package com.bill.manager.adapter.impl;

import com.bill.common.util.SpringUtils;
import com.bill.dao.db.ext.DownloadFileMapper;
import com.bill.manager.adapter.DownloadAdapter;
import com.bill.model.vo.param.DownloadParamVmo;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author liufeixiang
 * @date 2019-07-19 12:43
 */
public class ProductImpl extends DownloadAdapter {

    public ProductImpl(DownloadParamVmo downloadParamVmo) {
        super(downloadParamVmo);
    }

    @Override
    public void setHeader() {
        List<String> header = new LinkedList<>();
        header.add("产品名称");
        header.add("产品总数");
        header.add("已售");
        header.add("价格");
        super.headers = header;
    }

    @Override
    public void setItems(List<Map<String, Object>> list) {
        List<List<String>> data = new LinkedList<>();
        list.forEach(item -> {
            List<String> list1 = new LinkedList<>();
            list1.add(item.get("product_name") == null ? "-" : item.get("product_name").toString());
            list1.add(item.get("total") == null ? "-" : item.get("total").toString());
            list1.add(item.get("total_sold") == null ? "-" : item.get("total_sold").toString());
            list1.add(item.get("price") == null ? "-" : item.get("price").toString());
            data.add(list1);
        });
        super.datas = data;
    }

    @Override
    public List<Map<String, Object>> excuteSql() {
        Map<String, String> map = new HashMap<>(10);
        super.downloadParamVmo.getDownloadKeyParamVmos().forEach(item -> map.put(item.getDownloadItemKey(), item.getDownloadItemValue()));

        String start = "";
        if (StringUtils.isNotEmpty(map.get("start"))) {
            start = String.valueOf(map.get("start"));
        }
        String end = "";
        if (StringUtils.isNotEmpty(map.get("end"))) {
            end = String.valueOf(map.get("end"));
        }
        DownloadFileMapper downloadFileMapper = SpringUtils.getApplicationContext().getBean(DownloadFileMapper.class);
        List<Map<String, Object>> list = downloadFileMapper.listProduct(start, end);
        return list;
    }
}
