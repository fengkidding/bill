package com.bill.service;

import com.bill.model.po.auto.ProductBill;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.BillParamVO;
import com.bill.model.vo.param.QueryBillParamVO;
import com.bill.model.vo.param.StatisticsBillParamVO;
import com.bill.model.vo.param.QueryOrderParamVO;
import com.bill.model.vo.view.QueryProductBillVO;
import com.bill.model.vo.view.StatisticsBillVO;

import java.util.List;

/**
 * 账单service
 *
 * @author f
 * @date 2019-03-10
 */
public interface ProductBillService {

    /**
     * 保存账单信息
     *
     * @param productBill
     */
    void saveProductBill(ProductBill productBill);

    /**
     * 根据用户查询账单
     *
     * @param queryBillParamVO
     * @return
     */
    PageVO<List<QueryProductBillVO>> listProductBill(QueryBillParamVO queryBillParamVO);

    /**
     * 保存入账信息
     *
     * @param billParamVmo
     */
    void saveProductBill(BillParamVO billParamVmo);

    /**
     * 统计账单
     *
     * @param statisticsBillParamVmo
     * @return
     */
    List<StatisticsBillVO> statisticsBill(StatisticsBillParamVO statisticsBillParamVmo);

}
