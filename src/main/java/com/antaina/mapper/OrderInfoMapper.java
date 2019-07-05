package com.antaina.mapper;

import com.antaina.entity.order.OrderInfo;
import com.antaina.model.order.OrderInfoExportModel;
import com.antaina.model.order.OrderInfoQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderInfoMapper extends Mapper<OrderInfo> {

    List<OrderInfoQueryModel> getOrderListByParams(@Param("orderNo") String orderNo,
                                                   @Param("customerProductCode") String customerProductCode,
                                                   @Param("status") Integer status,
                                                   @Param("startTime") String startTime,
                                                   @Param("endTime") String endTime);

    List<OrderInfoExportModel> exportOrder(@Param("customerProductCode") String customerProductCode,
                                           @Param("orderNo") String orderNo,
                                           @Param("status") Integer status,
                                           @Param("startTime") String startTime,
                                           @Param("endTime") String endTime);
}
