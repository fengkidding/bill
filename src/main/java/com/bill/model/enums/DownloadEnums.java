package com.bill.model.enums;

/**
 * 下载类型枚举
 *
 * @author f
 * @date 2019-05-31
 */
public enum DownloadEnums {
    PRODUCT("com.bill.manager.adapter.impl.ProductImpl", "产品列表")
    ;

    /**
     * 实现类名称
     */
    private String className;

    /**
     * 备注
     */
    private String remark;

    DownloadEnums(String className, String remark) {
        this.className = className;
        this.remark = remark;
    }

    public String getClassName() {
        return className;
    }

    public String getRemark() {
        return remark;
    }
}
