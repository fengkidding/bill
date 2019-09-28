package com.bill.service;

import com.bill.model.entity.auto.ProductBill;
import com.bill.model.entity.auto.ProductOrder;
import com.bill.model.vmo.common.PageVmo;
import com.bill.model.vmo.param.BillParamVmo;
import com.bill.model.vmo.param.StatisticsBillParamVmo;
import com.bill.model.vmo.param.UserPageParamVmo;
import com.bill.model.vmo.view.QueryProductBill;
import com.bill.model.vmo.view.StatisticsBillVmo;

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
     * @param userPageParamVmo
     * @return
     */
    PageVmo<List<QueryProductBill>> listProductBill(UserPageParamVmo userPageParamVmo);

    /**
     * 保存入账信息
     *
     * @param billParamVmo
     */
    void saveProductBill(BillParamVmo billParamVmo);

    /**
     * 统计账单
     *
     * @param statisticsBillParamVmo
     * @return
     */
    List<StatisticsBillVmo> statisticsBill(StatisticsBillParamVmo statisticsBillParamVmo);

    /**
     * 订单收入入账
     *
     * @param productOrder
     */
    void saveProductBill(ProductOrder productOrder);
}
