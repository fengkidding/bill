package com.bill.service.impl;

import com.bill.common.util.CheckBeanUtil;
import com.bill.common.util.RemainingSumUtils;
import com.bill.common.util.SpringUtils;
import com.bill.dao.db.ext.ProductBillExtMapper;
import com.bill.model.constant.BillConstant;
import com.bill.model.conversion.ProductBillConversion;
import com.bill.model.entity.auto.ProductBill;
import com.bill.model.entity.auto.ProductOrder;
import com.bill.model.enums.TypeEnum;
import com.bill.model.vmo.common.PageVmo;
import com.bill.model.vmo.param.BillParamVmo;
import com.bill.model.vmo.param.StatisticsBillParamVmo;
import com.bill.model.vmo.param.UserPageParamVmo;
import com.bill.model.vmo.view.QueryProductBill;
import com.bill.model.vmo.view.StatisticsBillVmo;
import com.bill.service.OrderService;
import com.bill.service.ProductBillService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 账单service
 *
 * @author f
 * @date 2019-03-10
 */
@Service
public class ProductBillServiceImpl implements ProductBillService {

    @Autowired
    private ProductBillExtMapper productBillExtMapper;

    /**
     * 保存账单信息
     *
     * @param productBill
     */
    @Async
    @Override
    public void saveProductBill(ProductBill productBill) {
        productBillExtMapper.insertSelective(productBill);
    }

    /**
     * 根据用户查询账单
     *
     * @param userPageParamVmo
     * @return
     */
    @Override
    public PageVmo<List<QueryProductBill>> listProductBill(UserPageParamVmo userPageParamVmo) {
        if (!CheckBeanUtil.checkNotNullZero(userPageParamVmo.getOrderId())) {
            userPageParamVmo.setOrderId(null);
        }
        PageVmo<List<QueryProductBill>> pageVmo = new PageVmo<>();
        Page page = PageHelper.startPage(userPageParamVmo.getPageNum(), userPageParamVmo.getPageSize());
        List<ProductBill> list = productBillExtMapper.listProductBill(userPageParamVmo.getUserName(), userPageParamVmo.getStartTime(), userPageParamVmo.getEndTime(), userPageParamVmo.getOrderId());
        if (!CollectionUtils.isEmpty(list)) {
            List<QueryProductBill> vmoList = ProductBillConversion.PRODUCT_BILL_CONVERSION.entityToVmo(list);
            pageVmo.setTotal(page.getTotal());
            pageVmo.setData(vmoList);
        }
        return pageVmo;
    }

    /**
     * 保存收入入账信息
     *
     * @param billParamVmo
     */
    @Override
    public void saveProductBill(BillParamVmo billParamVmo) {
        ProductOrder productOrder = SpringUtils.getApplicationContext().getBean(OrderService.class).getProductOrder(billParamVmo.getProductOrderId());
        Long assetsMoney = RemainingSumUtils.getFen(billParamVmo.getAssetsMoney());
        Long rightsMoney = RemainingSumUtils.getFen(billParamVmo.getRightsMoney());

        //添加账单信息
        ProductBill productBill = new ProductBill();
        productBill.setBillUser(billParamVmo.getBillUser());
        productBill.setProductOrderId(billParamVmo.getProductOrderId());
        productBill.setAssetsMoney(assetsMoney);
        productBill.setProductType(productOrder.getProductType());
        productBill.setAssetsRemark(billParamVmo.getRemark() + BillConstant.MONEY_CHANGE + billParamVmo.getAssetsMoney());
        productBill.setRightsMoney(rightsMoney);
        productBill.setRightsRemark(billParamVmo.getRemark() + BillConstant.INCREASE_CHANGE + billParamVmo.getRightsMoney());
        this.saveProductBill(productBill);
    }

    /**
     * 统计账单
     *
     * @param statisticsBillParamVmo
     * @return
     */
    @Override
    public List<StatisticsBillVmo> statisticsBill(StatisticsBillParamVmo statisticsBillParamVmo) {
        if (!CheckBeanUtil.checkNotNullZero(statisticsBillParamVmo.getOrderId())) {
            statisticsBillParamVmo.setOrderId(null);
        }
        List<StatisticsBillVmo> result = new LinkedList<>();
        List<ProductBill> bills = productBillExtMapper.listProductBillAndDate(statisticsBillParamVmo.getUserName(), statisticsBillParamVmo.getStartTime(), statisticsBillParamVmo.getEndTime(), statisticsBillParamVmo.getOrderId());
        if (!CollectionUtils.isEmpty(bills)) {
            Long assetsMoney = 0L;
            Long rightsMoney = 0L;
            Long billMoney = 0L;

            Map<String, List<ProductBill>> map = bills.stream().collect(Collectors.groupingBy(ProductBill::getProductType));
            for (Map.Entry<String, List<ProductBill>> entry : map.entrySet()) {
                StatisticsBillVmo statisticsBillVmo1 = new StatisticsBillVmo();
                statisticsBillVmo1.setType(entry.getKey());
                Long typeAssetsMoney = 0L;
                Long typeRightsMoney = 0L;
                Long typeBillMoney = 0L;
                for (ProductBill productBill : entry.getValue()) {
                    assetsMoney += productBill.getAssetsMoney();
                    rightsMoney += productBill.getRightsMoney();
                    billMoney += productBill.getAssetsMoney() + productBill.getRightsMoney();

                    typeAssetsMoney += productBill.getAssetsMoney();
                    typeRightsMoney += productBill.getRightsMoney();
                    typeBillMoney += productBill.getAssetsMoney() + productBill.getRightsMoney();
                }
                statisticsBillVmo1.setBillMoney(RemainingSumUtils.getYuan(typeBillMoney));
                statisticsBillVmo1.setAssetsMoney(RemainingSumUtils.getYuan(typeAssetsMoney));
                statisticsBillVmo1.setRightsMoney(RemainingSumUtils.getYuan(typeRightsMoney));
                result.add(statisticsBillVmo1);
            }

            StatisticsBillVmo statisticsBillVmo = new StatisticsBillVmo();
            statisticsBillVmo.setType(TypeEnum.ALL.getType());
            statisticsBillVmo.setAssetsMoney(RemainingSumUtils.getYuan(assetsMoney));
            statisticsBillVmo.setRightsMoney(RemainingSumUtils.getYuan(rightsMoney));
            statisticsBillVmo.setBillMoney(RemainingSumUtils.getYuan(billMoney));
            result.add(statisticsBillVmo);
        }

        return result;
    }

    /**
     * 订单收入入账
     *
     * @param productOrder
     */
    @Override
    public void saveProductBill(ProductOrder productOrder) {
        ProductBill productBill = new ProductBill();
        productBill.setBillUser(productOrder.getOrderUser());
        productBill.setProductOrderId(productOrder.getId());
        productBill.setProductType(productOrder.getProductType());
        productBill.setAssetsMoney(-productOrder.getPrice());
        productBill.setAssetsRemark(BillConstant.BUY_PRODUCT + productOrder.getProductName() + productOrder.getRemark() + BillConstant.MONEY_REDUCE + RemainingSumUtils.getYuan(productOrder.getPrice()));
        productBill.setRightsMoney(-productOrder.getPrice());
        productBill.setRightsRemark(BillConstant.RIGHTS_DOWN + RemainingSumUtils.getYuan(productOrder.getPrice()));

        ProductBill productBill1 = new ProductBill();
        productBill1.setBillUser(productOrder.getOrderUser());
        productBill1.setProductOrderId(productOrder.getId());
        productBill1.setProductType(productOrder.getProductType());
        productBill1.setAssetsMoney(productOrder.getPrice());
        productBill1.setAssetsRemark(BillConstant.GET_PRODUCT + productOrder.getProductName() + productOrder.getRemark() + BillConstant.INCREASE_IN_KIND + RemainingSumUtils.getYuan(productOrder.getPrice()));
        productBill1.setRightsMoney(productOrder.getPrice());
        productBill1.setRightsRemark(BillConstant.RIGHTS_UP + RemainingSumUtils.getYuan(productOrder.getPrice()));

        ProductBill productBill2 = new ProductBill();
        productBill2.setBillUser(productOrder.getOrderUser());
        productBill2.setProductOrderId(productOrder.getId());
        productBill2.setProductType(productOrder.getProductType());
        productBill2.setAssetsMoney(-productOrder.getPrice());
        productBill2.setAssetsRemark(BillConstant.USE_PRODUCT + productOrder.getProductName() + productOrder.getRemark() + BillConstant.INCREASE_REDUCE + RemainingSumUtils.getYuan(productOrder.getPrice()));
        productBill2.setRightsMoney(-productOrder.getPrice());
        productBill2.setRightsRemark(BillConstant.RIGHTS_DOWN + RemainingSumUtils.getYuan(productOrder.getPrice()));

        this.saveProductBill(productBill);
        this.saveProductBill(productBill1);
        this.saveProductBill(productBill2);
    }

}
