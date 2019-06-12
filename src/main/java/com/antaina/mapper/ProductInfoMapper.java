package com.antaina.mapper;

import com.antaina.entity.product.ProductInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ProductInfoMapper extends Mapper<ProductInfo> {
    List<ProductInfo> getListByParams(@Param("productCode")String productCode, @Param("productName")String productName, @Param("type")Integer type);
}
