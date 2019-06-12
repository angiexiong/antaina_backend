package com.antaina.mapper;

import com.antaina.entity.customer.CustomerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CustomerInfoMapper extends Mapper<CustomerInfo> {
    List<CustomerInfo> getCustomerListByParams(@Param("name") String name);
}
