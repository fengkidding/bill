package com.bill.model.enums;

/**
 * 类型枚举
 *
 * @author f
 * @date 2019-07-03
 */
public enum TypeEnum {
    ALL("all", "合计");

    private String type;

    private String remark;

    TypeEnum(String type, String remark) {
        this.type = type;
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public String getRemark() {
        return remark;
    }
}
