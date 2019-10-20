package com.bill.service.impl;

import com.bill.common.util.CheckBeanUtils;
import com.bill.common.util.ComputeUtils;
import com.bill.dao.db.ext.ProductBillExtMapper;
import com.bill.model.constant.BillConstant;
import com.bill.model.conversion.ProductBillConversion;
import com.bill.model.entity.auto.Classification;
import com.bill.model.entity.auto.ProductBill;
import com.bill.model.enums.TypeEnum;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.BillParamVO;
import com.bill.model.vo.param.StatisticsBillParamVO;
import com.bill.model.vo.param.UserPageParamVO;
import com.bill.model.vo.view.QueryProductBillVO;
import com.bill.model.vo.view.StatisticsBillVO;
import com.bill.service.ClassificationSerrvice;
import com.bill.service.ProductBillService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
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

    @Autowired
    private ClassificationSerrvice classificationSerrvice;

    /**
     * 保存账单信息
     *
     * @param productBill
     */
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
    public PageVO<List<QueryProductBillVO>> listProductBill(UserPageParamVO userPageParamVmo) {
        if (!CheckBeanUtils.checkNotNullZero(userPageParamVmo.getClassificationId())) {
            userPageParamVmo.setClassificationId(null);
        }
        PageVO<List<QueryProductBillVO>> pageVmo = new PageVO<>();
        Page page = PageHelper.startPage(userPageParamVmo.getPageNum(), userPageParamVmo.getPageSize());
        List<ProductBill> list = productBillExtMapper.listProductBill(userPageParamVmo.getUserName(), userPageParamVmo.getStartTime(), userPageParamVmo.getEndTime(), userPageParamVmo.getClassificationId());
        if (!CollectionUtils.isEmpty(list)) {
            List<QueryProductBillVO> vmoList = ProductBillConversion.PRODUCT_BILL_CONVERSION.entityToVmo(list);
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
    public void saveProductBill(BillParamVO billParamVmo) {
        Long money = ComputeUtils.getFen(billParamVmo.getMoney());

        //添加账单信息
        ProductBill productBill = new ProductBill();
        productBill.setBillUser(billParamVmo.getBillUser());
        productBill.setAssetsMoney(money);
        productBill.setAssetsRemark(billParamVmo.getRemark() + BillConstant.MONEY_CHANGE + billParamVmo.getMoney());
        productBill.setRightsMoney(money);
        productBill.setRightsRemark(billParamVmo.getRemark() + BillConstant.RIGHTS_CHANGE + billParamVmo.getMoney());
        productBill.setClassificationId(billParamVmo.getClassificationId());
        this.saveProductBill(productBill);
    }

    /**
     * 统计账单
     *
     * @param statisticsBillParamVmo
     * @return
     */
    @Override
    public List<StatisticsBillVO> statisticsBill(StatisticsBillParamVO statisticsBillParamVmo) {
        List<StatisticsBillVO> result = new LinkedList<>();
        List<ProductBill> bills = productBillExtMapper.listProductBillAndDate(statisticsBillParamVmo.getUserName(), statisticsBillParamVmo.getStartTime(), statisticsBillParamVmo.getEndTime());
        if (!CollectionUtils.isEmpty(bills)) {
            Long assetsMoney = 0L;
            Long rightsMoney = 0L;
            Long billMoney = 0L;

            //数据根据类型id分组
            Map<Integer, List<ProductBill>> map = bills.stream().collect(Collectors.groupingBy(ProductBill::getClassificationId));
            List<Classification> classifications = classificationSerrvice.listClassificationByIds(new ArrayList<>(map.keySet()));
            Map<Integer, Classification> classificationMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(classifications)) {
                classificationMap = Maps.uniqueIndex(classifications, Classification::getId);
            }

            for (Map.Entry<Integer, List<ProductBill>> entry : map.entrySet()) {
                StatisticsBillVO statisticsBillVmo1 = new StatisticsBillVO();

                Classification classification = classificationMap.get(entry.getKey());
                statisticsBillVmo1.setType(classification == null ? "" : classification.getClassificationName());
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
                statisticsBillVmo1.setBillMoney(ComputeUtils.getYuan(typeBillMoney));
                statisticsBillVmo1.setAssetsMoney(ComputeUtils.getYuan(typeAssetsMoney));
                statisticsBillVmo1.setRightsMoney(ComputeUtils.getYuan(typeRightsMoney));
                result.add(statisticsBillVmo1);
            }

            StatisticsBillVO statisticsBillVmo = new StatisticsBillVO();
            statisticsBillVmo.setType(TypeEnum.ALL.getType());
            statisticsBillVmo.setAssetsMoney(ComputeUtils.getYuan(assetsMoney));
            statisticsBillVmo.setRightsMoney(ComputeUtils.getYuan(rightsMoney));
            statisticsBillVmo.setBillMoney(ComputeUtils.getYuan(billMoney));
            result.add(statisticsBillVmo);
        }

        return result;
    }

}
