package com.antaina.mapper;

import com.antaina.entity.order.OrderDeliveryDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;

@Repository
public interface OrderDeliveryDetailMapper extends Mapper<OrderDeliveryDetail> {

    BigDecimal getTotalAmountByOrderId(@Param("orderId")Long orderId);
}
