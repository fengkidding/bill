package com.bill.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bill.common.util.AuthContextUtils;
import com.bill.common.util.CheckBeanUtils;
import com.bill.common.util.ComputeUtils;
import com.bill.dao.db.ext.ProductOrderExtMapper;
import com.bill.manager.MemberManager;
import com.bill.model.constant.NumberConstant;
import com.bill.model.constant.RabbitExchangeConstant;
import com.bill.model.constant.RabbitRoutingKeyConstant;
import com.bill.model.conversion.ProductOrderConversion;
import com.bill.model.dto.PayOrderMsgDto;
import com.bill.model.enums.ResultEnum;
import com.bill.model.exception.ServiceException;
import com.bill.model.po.auto.Product;
import com.bill.model.po.auto.ProductOrder;
import com.bill.model.vo.common.PageVO;
import com.bill.model.vo.param.OrderParamVO;
import com.bill.model.vo.param.PayOrderParamVO;
import com.bill.model.vo.param.QueryOrderParamVO;
import com.bill.model.vo.view.QueryOrderVO;
import com.bill.service.OrderService;
import com.bill.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
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
        Integer memberId = AuthContextUtils.getLoginMemberId();
        Product product = productService.getProduct(orderParamVmo.getProductId());
        if (null == product) {
            throw new ServiceException(ResultEnum.PRODUCT_NONE_ERROR);
        }

        //保存订单信息
        ProductOrder productOrder = new ProductOrder();
        productOrder.setMemberId(memberId);
        productOrder.setProductId(orderParamVmo.getProductId());
        productOrder.setRemark(orderParamVmo.getRemark());
        productOrder.setProductName(product.getProductName());
        productOrder.setTotal(orderParamVmo.getTotal());
        Long price = orderParamVmo.getTotal() * product.getPrice();
        productOrder.setPrice(price);
        productOrder.setClassificationId(product.getClassificationId());
        productOrderExtMapper.saveSelective(productOrder);

        //更新库存
        productService.soldProduct(orderParamVmo.getProductId(), orderParamVmo.getTotal());

        return productOrder.getId();
    }

    /**
     * 分页根据用户查询订单
     *
     * @param memberId
     * @return
     */
    @Override
    public PageVO<List<QueryOrderVO>> listOrder(QueryOrderParamVO orderPageParamVmo, Integer memberId) {
        PageVO<List<QueryOrderVO>> pageVmo = new PageVO<>();
        if (!CheckBeanUtils.checkNotNullZero(orderPageParamVmo.getClassificationId())) {
            orderPageParamVmo.setClassificationId(null);
        }
        Page page = PageHelper.startPage(orderPageParamVmo.getPageNum(), orderPageParamVmo.getPageSize());
        List<ProductOrder> list = productOrderExtMapper.listOrder(memberId, orderPageParamVmo.getClassificationId());
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
     * @param memberId
     * @return
     */
    @Override
    public List<ProductOrder> listProductOrder(Integer memberId, LocalDateTime startTime, LocalDateTime endTime) {
        return productOrderExtMapper.listOrderAndDate(memberId, startTime, endTime);
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

    /**
     * 更新订单
     *
     * @param productOrder
     */
    @Override
    public void updateOrder(ProductOrder productOrder) {
        productOrderExtMapper.updateByPrimaryKeySelective(productOrder);
    }

    /**
     * 支付订单
     *
     * @param payOrderParamVO
     */
    @Override
    public void payOrder(PayOrderParamVO payOrderParamVO) {
        ProductOrder productOrder = this.getProductOrder(payOrderParamVO.getOrderId());
        if (null == productOrder) {
            throw new ServiceException(ResultEnum.ORDER_NONE_ERROR);
        }
        Long amount = ComputeUtils.getFen(payOrderParamVO.getAmount());

        //扣除用户余额
        boolean result = memberManager.updateRemainingSum(productOrder.getMemberId(), -amount);
        if (!result) {
            throw new ServiceException(ResultEnum.DEDUCT_REMAININGSUM_ERROR);
        }

        //更新订单
        ProductOrder productOrderStatus = new ProductOrder();
        productOrderStatus.setId(productOrder.getId());
        productOrderStatus.setStatus(NumberConstant.BYTE_ONE);
        productOrderStatus.setPayPrice(amount);
        this.updateOrder(productOrderStatus);

        //用户收钱
        Product product = productService.getProduct(productOrder.getProductId());
        PayOrderMsgDto payOrderMsgDto = new PayOrderMsgDto();
        payOrderMsgDto.setMemberId(product.getMemberId());
        payOrderMsgDto.setRemainingSum(productOrder.getPrice());
        payOrderMsgDto.setOrderId(payOrderParamVO.getOrderId());
        Message message = MessageBuilder.withBody(JSONObject.toJSONString(payOrderMsgDto).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setMessageId(productOrder.getId().toString()).build();
        rabbitTemplate.send(RabbitExchangeConstant.PAY_ORDER_EXCHANGE, RabbitRoutingKeyConstant.PAY_ORDER_EXCHANGE_ROUTING, message);
    }

}
