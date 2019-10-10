package com.bill.controller;


import com.bill.model.enums.ResultEnum;
import com.bill.model.vo.common.ResultVO;

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
    protected ResultVO resultSuccess() {
        ResultVO resultVmo = new ResultVO();
        resultVmo.setMsg(ResultEnum.SUCCESS.getMsg());
        return resultVmo;
    }

    /**
     * 封装返回成功结果
     *
     * @param object
     * @return
     */
    protected ResultVO resultSuccess(Object object) {
        ResultVO resultVmo = new ResultVO();
        resultVmo.setMsg(ResultEnum.SUCCESS.getMsg());
        resultVmo.setData(object);
        return resultVmo;
    }

    /**
     * 封装返回失败结果
     *
     * @return
     */
    protected ResultVO resultFailed() {
        ResultVO resultVmo = new ResultVO();
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
    protected ResultVO resultFailed(Integer code, String msg) {
        ResultVO resultVmo = new ResultVO();
        resultVmo.setCode(code);
        resultVmo.setMsg(msg);
        return resultVmo;
    }
}
