package com.bill.model.constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 通用常量
 *
 * @author f
 * @date 2019-12-06
 */
public class CommonConstant {

    /**
     * null
     */
    public static final String STRING_NULL = "null";

    /**
     * 空数组
     */
    public static final List EMPTY_LIST = Collections.unmodifiableList(new ArrayList<>(0));

    private CommonConstant() {
    }
}
