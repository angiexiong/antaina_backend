package com.antaina.mapper;

import com.antaina.entity.order.OrderInfo;
import com.antaina.model.order.OrderInfoQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderInfoMapper extends Mapper<OrderInfo> {

    List<OrderInfoQueryModel> getOrderListByParams(@Param("customerProductCode") String customerProductCode,
                                                   @Param("productCode") String productCode,
                                                   @Param("status") Integer status,
                                                   @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime);
}
