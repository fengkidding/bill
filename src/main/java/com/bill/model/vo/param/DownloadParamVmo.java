package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 下载参数
 *
 * @author f
 * @date 2019-04-09
 */
@ApiModel(value = "下载参数")
public class DownloadParamVmo {

    @ApiModelProperty(value = "下载数据类型：PRODUCT产品列表", example = "PRODUCT")
    @NotNull(message = "下载数据类型不能为空！")
    private String downloadType;

    @ApiModelProperty(value = "是否强制更新：0否，1是，1得情况下redis缓存失效", example = "0")
    private Integer isMandatoryUpdate;

    @Valid
    @NotNull(message = "下载参数不能为null！")
    @ApiModelProperty(value = "下载参数")
    private List<DownloadKeyParamVmo> downloadKeyParamVmos;

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public Integer getIsMandatoryUpdate() {
        return isMandatoryUpdate;
    }

    public void setIsMandatoryUpdate(Integer isMandatoryUpdate) {
        this.isMandatoryUpdate = isMandatoryUpdate;
    }

    public List<DownloadKeyParamVmo> getDownloadKeyParamVmos() {
        return downloadKeyParamVmos;
    }

    public void setDownloadKeyParamVmos(List<DownloadKeyParamVmo> downloadKeyParamVmos) {
        this.downloadKeyParamVmos = downloadKeyParamVmos;
    }
}
