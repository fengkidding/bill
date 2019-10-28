package com.bill.core.factory;

import com.bill.model.enums.ResultEnum;
import com.bill.model.vo.common.ResultVO;

/**
 * 接口返回对象工厂
 *
 * @author f
 * @date 2019-10-23
 */
public class ResultFactory {

    /**
     * 饿汉，成功返回对象
     */
    private static final ResultVO successResult = new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());

    /**
     * 懒汉，熔断回滚返回对象
     */
    private static ResultVO fallBackResult;

    /**
     * 防止实例化
     */
    private ResultFactory() {
    }

    /**
     * 内部类验证,异常返回对象
     *
     * @return
     */
    private static class GetError {
        private static final ResultVO errorResult = new ResultVO(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
    }

    /**
     * 获取成功返回对象
     *
     * @return
     */
    public static final ResultVO getSuccessResult() {
        return successResult;
    }

    /**
     * 获取成功返回对象
     *
     * @return
     */
    public static final ResultVO getSuccessResult(Object data) {
        ResultVO resultVO = new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 获取异常返回对象
     *
     * @return
     */
    public static final ResultVO getErrorResult() {
        return GetError.errorResult;
    }

    /**
     * 双重验证，熔断回滚返回对象
     *
     * @return
     */
    public static final ResultVO getFallBackResult() {
        if (null == fallBackResult) {
            synchronized (ResultFactory.class) {
                if (null == fallBackResult) {
                    fallBackResult = new ResultVO(ResultEnum.FALL_BACK.getCode(), ResultEnum.FALL_BACK.getMsg());
                }
            }
        }
        return fallBackResult;
    }

    /**
     * 获取自定义返回对象
     *
     * @param resultEnum
     * @return
     */
    public static final ResultVO getResult(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO(resultEnum.getCode(), resultEnum.getMsg());
        return resultVO;
    }

    /**
     * 获取自定义返回对象
     *
     * @param resultEnum
     * @param msg
     * @return
     */
    public static final ResultVO getResult(ResultEnum resultEnum, String msg) {
        ResultVO resultVO = new ResultVO(resultEnum.getCode(), msg);
        return resultVO;
    }
}
