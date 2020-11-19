package com.bill.controller;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.model.vo.common.ResultVO;
import com.bill.model.vo.param.DownloadParamVmo;
import com.bill.model.vo.view.DownloadVmo;
import com.bill.service.DownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 下载接口
 *
 * @author f
 * @date 2091-04-03
 */
@Api(description = "下载接口")
@RestController
@RequestMapping(value = "/download")
public class DownloadController extends BaseController {

    @Autowired
    private DownloadService downloadService;

    /**
     * 下载excel文件
     *
     * @param downloadParamVmo
     * @return
     */
    @ApiOperation(value = "下载excel文件接口")
    @PostMapping(value = "/download-excel")
    public ResultVO<String> downloadExcel(@Valid @RequestBody DownloadParamVmo downloadParamVmo) {
        LogBackUtils.info("downloadExcel:" + JSON.toJSONString(downloadParamVmo));
        return super.resultSuccess(downloadService.downloadExcel(downloadParamVmo));
    }

    /**
     * 获取下载结果
     *
     * @param key
     * @return
     */
    @ApiOperation(value = "获取下载结果")
    @GetMapping(value = "/get_downloadvmo/{key}")
    public ResultVO<DownloadVmo> getDownloadVmo(@PathVariable("key") String key) {
        DownloadVmo downloadVmo = downloadService.getDownloadKeyLink(key);
        if (null == downloadVmo) {
            return super.resultError();
        }
        return super.resultSuccess(downloadVmo);
    }
}
