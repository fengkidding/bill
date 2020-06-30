package com.bill.model.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 根据excel导入产品参数
 *
 * @author f
 * @date 2020-06-30
 */
@ApiModel(value = "根据excel导入产品参数")
public class SaveProductForExcelVO {

    @NotNull(message = "文件名不能为null！")
    @ApiModelProperty(value = "文件名")
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
