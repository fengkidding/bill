package com.bill.model.vo.view;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 下载返回值vmo
 *
 * @author f
 * @date 2019-04-03
 */
@ApiModel(value = "下载返回值vmo")
public class DownloadVmo {

    @ApiModelProperty(value = "本地文件名称")
    private String localFileName;

    @ApiModelProperty(value = "是否上传完成：0无数据，1未完成，2已完成，3数据异常请重试")
    private Integer completeStatus;

    @ApiModelProperty(value = "下载链接")
    private String link;

    @ApiModelProperty(value = "数据当前页数")
    protected Integer pageNum;

    @ApiModelProperty(value = "总页数")
    protected Integer totalPageNum;

    @ApiModelProperty(value = "下载进度单位%，是当前下载页数与总页数比值")
    private BigDecimal downloadProgress;

    public String getLocalFileName() {
        return localFileName;
    }

    public void setLocalFileName(String localFileName) {
        this.localFileName = localFileName;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public Integer getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(Integer completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public BigDecimal getDownloadProgress() {
        return downloadProgress;
    }

    public void setDownloadProgress(BigDecimal downloadProgress) {
        this.downloadProgress = downloadProgress;
    }

}
