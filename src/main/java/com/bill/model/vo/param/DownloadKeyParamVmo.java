package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 下载参数
 *
 * @author f
 * @date 2019-04-09
 */
@ApiModel(value = "下载条件参数")
public class DownloadKeyParamVmo {

    @ApiModelProperty(value = "下载数据条件key")
    @NotNull(message = "下载数据条件key不能为空！")
    private String downloadItemKey;

    @ApiModelProperty(value = "下载数据条件值")
    @NotNull(message = "下载数据条件值不能为null！")
    private String downloadItemValue;

    public String getDownloadItemKey() {
        return downloadItemKey;
    }

    public void setDownloadItemKey(String downloadItemKey) {
        this.downloadItemKey = downloadItemKey;
    }

    public String getDownloadItemValue() {
        return downloadItemValue;
    }

    public void setDownloadItemValue(String downloadItemValue) {
        this.downloadItemValue = downloadItemValue;
    }
}
