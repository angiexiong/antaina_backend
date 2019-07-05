package com.antaina.service.order;

import com.antaina.entity.order.OrderDeliveryDetail;
import com.antaina.entity.order.OrderInfo;
import com.antaina.entity.product.ProductInfo;
import com.antaina.enums.MsgResult;
import com.antaina.enums.common.OrderStatusEnum;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.OrderDeliveryDetailMapper;
import com.antaina.mapper.OrderInfoMapper;
import com.antaina.mapper.ProductInfoMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.order.OrderInfoExportModel;
import com.antaina.model.order.OrderInfoModel;
import com.antaina.model.order.OrderInfoQueryModel;
import com.antaina.service.product.ProductInfoService;
import com.antaina.util.PageUtil;
import com.antaina.util.UidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderDeliveryDetailMapper orderDeliveryDetailMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String orderNo, String customerProductCode, Integer status, String startTime, String endTime) {
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<OrderInfoQueryModel> orderInfoList = orderInfoMapper.getOrderListByParams(orderNo, customerProductCode, status, startTime, endTime);
        return PageUtil.create(orderInfoList);
    }

    public void add(OrderInfoModel orderInfoModel) {
        ProductInfo condition = new ProductInfo();
        condition.setCustomerProductCode(orderInfoModel.getCustomerProductCode());
        List<ProductInfo> productInfoList = productInfoMapper.select(condition);
        if(CollectionUtils.isEmpty(productInfoList)){
            throw new BusinessException(MsgResult.CUSTOMER_PRODUCT_INFO_EXIST_NO);
        }

        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoModel, orderInfo);
        orderInfo.setId(UidUtil.getInstance().nextId());
        orderInfo.setDeliveryAmount(BigDecimal.ZERO);
        orderInfo.setRemainingAmount(BigDecimal.ZERO);
        orderInfo.setStatus(OrderStatusEnum.UNFINISHED.ordinal());
        orderInfo.setDeliveryDate(orderInfoModel.getDeliveryDate());
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderInfoMapper.insert(orderInfo);
    }

    public void update(OrderInfoModel orderInfoModel) {
        if (null == orderInfoModel.getId()) {
            throw new BusinessException(MsgResult.USER_ID_EMPTY);
        }
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderInfoModel.getId());
        BeanUtils.copyProperties(orderInfoModel, orderInfo);
        orderInfo.setUpdateTime(new Date());
        orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if (null != id) {
            OrderInfo materialInfo = orderInfoMapper.selectByPrimaryKey(id);
            if (materialInfo != null) {
                orderInfoMapper.deleteByPrimaryKey(id);

                OrderDeliveryDetail odd = new OrderDeliveryDetail();
                odd.setOrderId(id);
                orderDeliveryDetailMapper.delete(odd);
            }
        }
    }

    public OrderInfo getById(Long id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByCustomerProductCode(String customerProductCode) {
        if (StringUtils.isNotBlank(customerProductCode)) {
            OrderInfo condition = new OrderInfo();
            condition.setCustomerProductCode(customerProductCode);
            List<OrderInfo> orderInfoList = orderInfoMapper.select(condition);
            if (!CollectionUtils.isEmpty(orderInfoList)) {
                orderInfoList.forEach(e -> {
                    delete(e.getId());
                });
            }
        }
    }

    public List<OrderInfoExportModel> exportOrder(String customerProductCode, String orderNo, Integer type, String startTime, String endTime) {
        return orderInfoMapper.exportOrder(customerProductCode, orderNo, type, startTime, endTime);
    }
}
