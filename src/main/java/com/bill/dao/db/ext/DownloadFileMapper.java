package com.bill.dao.db.ext;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 下载文件mapper
 */
public interface DownloadFileMapper {

    /**
     * 根据时间查询产品列表
     *
     * @param start
     * @param end
     * @return
     */
    List<Map<String, Object>> listProduct(@Param("start") String start, @Param("end") String end);
}
