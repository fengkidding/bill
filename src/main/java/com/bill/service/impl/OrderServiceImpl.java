package com.bill.service.impl;

import com.bill.common.util.ComputeUtils;
import com.bill.dao.db.ext.ProductOrderExtMapper;
import com.bill.manager.MemberManager;
import com.bill.model.constant.RabbitExchangeConstant;
import com.bill.model.constant.RabbitQueueConstant;
import com.bill.model.conversion.ProductOrderConversion;
import com.bill.model.dto.ConsumerUserSumBO;
import com.bill.model.po.auto.Product;
import com.bill.model.po.auto.ProductOrder;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.OrderParamVO;
import com.bill.model.vo.view.QueryOrderVO;
import com.bill.service.OrderService;
import com.bill.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private ProductOrderExtMapper productOrderExtMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberManager memberManager;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 用户下单
     *
     * @param orderParamVmo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer createOrder(OrderParamVO orderParamVmo) {
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
        boolean result = memberManager.updateRemainingSum(orderParamVmo.getOrderUser(), -price);
        if (!result) {
            throw new RuntimeException("扣除用户余额失败");
        }

        //用户收钱
        ConsumerUserSumBO consumerUserSumBO = new ConsumerUserSumBO();
        consumerUserSumBO.setUserName(product.getUserName());
        consumerUserSumBO.setRemainingSum(price);
        rabbitTemplate.convertAndSend(RabbitExchangeConstant.MEMBER_REMAINING_SUM, RabbitQueueConstant.MEMBER_REMAINING_SUM, consumerUserSumBO);

        return productOrder.getId();
    }

    /**
     * 分页根据用户查询订单
     *
     * @param userName
     * @return
     */
    @Override
    public PageVO<List<QueryOrderVO>> listOrder(Integer pageNum, Integer pageSize, String userName) {
        PageVO<List<QueryOrderVO>> pageVmo = new PageVO<>();
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<ProductOrder> list = productOrderExtMapper.listOrder(userName);
        pageVmo.setTotal(page.getTotal());
        if (!CollectionUtils.isEmpty(list)) {
            List<QueryOrderVO> vmoList = ProductOrderConversion.PRODUCT_ORDER_CONVERSION.entityToVmo(list);
            for (int i = 0; i < vmoList.size(); i++) {
                vmoList.get(i).setPrice(ComputeUtils.getYuan(list.get(i).getPrice()));
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
