package com.bill.service.impl;

import com.alibaba.fastjson.JSON;
import com.bill.common.util.RemainingSumUtils;
import com.bill.dao.db.ext.ProductOrderExtMapper;
import com.bill.model.bmo.ConsumerUserSumParamVmo;
import com.bill.model.conversion.ProductOrderConversion;
import com.bill.model.entity.auto.Product;
import com.bill.model.entity.auto.ProductOrder;
import com.bill.model.vmo.common.PageVmo;
import com.bill.model.vmo.common.ResultVmo;
import com.bill.model.vmo.param.OrderParamVmo;
import com.bill.model.vmo.view.QueryOrder;
import com.bill.service.OrderService;
import com.bill.service.ProductService;
import com.bill.service.client.UserFeign;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单接口
 *
 * @author f
 * @date 2019-03-10
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductOrderExtMapper productOrderExtMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserFeign userFeign;

    /**
     * 用户下单
     *
     * @param orderParamVmo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer createOrder(OrderParamVmo orderParamVmo) {
        //更新商品
        Product product = productService.getProduct(orderParamVmo.getProductId());
        productService.soldProduct(orderParamVmo.getProductId(), orderParamVmo.getTotal());

        //保存订单信息
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrderUser(orderParamVmo.getOrderUser());
        productOrder.setProductId(orderParamVmo.getProductId());
        productOrder.setRemark(orderParamVmo.getRemark());
        productOrder.setProductName(product.getProductName());
        productOrder.setTotal(orderParamVmo.getTotal());
        productOrder.setStatus((byte) 1);
        Long price = orderParamVmo.getTotal() * product.getPrice();
        productOrder.setPrice(price);
        productOrderExtMapper.saveSelective(productOrder);

        //扣除用户余额
        ConsumerUserSumParamVmo consumerUserSumParamVmo = new ConsumerUserSumParamVmo();
        consumerUserSumParamVmo.setUserName(orderParamVmo.getOrderUser());
        consumerUserSumParamVmo.setRemainingSum(-price);
        ResultVmo resultVmo = userFeign.updateRemainingSum(consumerUserSumParamVmo);
        logger.info("createOrder-扣除用户余额：resultVmo=" + JSON.toJSONString(resultVmo));

        return productOrder.getId();
    }

    /**
     * 分页根据用户查询订单
     *
     * @param userName
     * @return
     */
    @Override
    public PageVmo<List<QueryOrder>> listOrder(Integer pageNum, Integer pageSize, String userName) {
        PageVmo<List<QueryOrder>> pageVmo = new PageVmo<>();
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<ProductOrder> list = productOrderExtMapper.listOrder(userName);
        pageVmo.setTotal(page.getTotal());
        if (!CollectionUtils.isEmpty(list)) {
            List<QueryOrder> vmoList = ProductOrderConversion.PRODUCT_ORDER_CONVERSION.entityToVmo(list);
            for (int i = 0; i < vmoList.size(); i++) {
                vmoList.get(i).setPrice(RemainingSumUtils.getYuan(list.get(i).getPrice()));
            }
            pageVmo.setData(vmoList);
        }
        return pageVmo;
    }

    /**
     * 根据用户查询订单
     *
     * @param userName
     * @return
     */
    @Override
    public List<ProductOrder> listProductOrder(String userName, LocalDateTime startTime, LocalDateTime endTime) {
        return productOrderExtMapper.listOrderAndDate(userName, startTime, endTime);
    }

    /**
     * 根据id查询订单
     *
     * @param orderId
     * @return
     */
    @Override
    public ProductOrder getProductOrder(Integer orderId) {
        return productOrderExtMapper.getProductOrder(orderId);
    }

}
