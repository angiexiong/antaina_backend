package com.antaina.mapper;

import com.antaina.entity.order.OrderDeliveryDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrderDeliveryDetailMapper extends Mapper<OrderDeliveryDetail> {
}
