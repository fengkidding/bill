package com.bill.controller;


import com.bill.model.enums.ResultEnum;
import com.bill.model.vmo.common.ResultVmo;

/**
 * Controller 父类
 *
 * @author f
 * @date 2019-02-22
 */
public class BaseController {

    /**
     * 封装返回成功结果
     *
     * @return
     */
    protected ResultVmo resultSuccess() {
        ResultVmo resultVmo = new ResultVmo();
        resultVmo.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultVmo;
    }

    /**
     * 封装返回成功结果
     *
     * @param object
     * @return
     */
    protected ResultVmo resultSuccess(Object object) {
        ResultVmo resultVmo = new ResultVmo();
        resultVmo.setMsg(ResultEnum.SUCCESS.getMsg());
        resultVmo.setData(object);
        return resultVmo;
    }

    /**
     * 封装返回失败结果
     *
     * @return
     */
    protected ResultVmo resultFailed() {
        ResultVmo resultVmo = new ResultVmo();
        resultVmo.setCode(ResultEnum.ERROR.getCode());
        resultVmo.setMsg(ResultEnum.ERROR.getMsg());
        return resultVmo;
    }

    /**
     * 封装返回失败结果
     *
     * @param code
     * @param msg
     * @return
     */
    protected ResultVmo resultFailed(Integer code, String msg) {
        ResultVmo resultVmo = new ResultVmo();
        resultVmo.setCode(code);
        resultVmo.setMsg(msg);
        return resultVmo;
    }
}
