package com.antaina.service.order;

import com.antaina.entity.order.OrderDeliveryDetail;
import com.antaina.entity.order.OrderInfo;
import com.antaina.enums.MsgResult;
import com.antaina.enums.common.OrderStatusEnum;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.OrderDeliveryDetailMapper;
import com.antaina.mapper.OrderInfoMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.order.OrderInfoModel;
import com.antaina.model.order.OrderInfoQueryModel;
import com.antaina.util.PageUtil;
import com.antaina.util.UidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderDeliveryDetailMapper orderDeliveryDetailMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String customerProductCode, String productCode, Integer status, String startTime, String endTime){
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<OrderInfoQueryModel> orderInfoList = orderInfoMapper.getOrderListByParams(customerProductCode, productCode, status, startTime, endTime);
        return PageUtil.create(orderInfoList);
    }

    public void add(OrderInfoModel orderInfoModel){
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderInfoModel, orderInfo);
        orderInfo.setId(UidUtil.getInstance().nextId());
        orderInfo.setDeliveryAmount(BigDecimal.ZERO);
        orderInfo.setRemainingAmount(BigDecimal.ZERO);
        orderInfo.setStatus(OrderStatusEnum.UNFINISHED.ordinal());
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderInfoMapper.insert(orderInfo);
    }

    public void update(OrderInfoModel orderInfoModel){
        if(null == orderInfoModel.getId()){
            throw new BusinessException(MsgResult.USER_ID_EMPTY);
        }
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderInfoModel.getId());
        BeanUtils.copyProperties(orderInfoModel, orderInfo);
        orderInfo.setUpdateTime(new Date());
        orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        if(null != id){
            OrderInfo materialInfo = orderInfoMapper.selectByPrimaryKey(id);
            if(materialInfo != null){
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
}
