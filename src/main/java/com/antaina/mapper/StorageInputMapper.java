package com.antaina.mapper;

import com.antaina.entity.storage.StorageInput;
import com.antaina.model.storage.StorageInputQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface StorageInputMapper extends Mapper<StorageInput> {
    List<StorageInputQueryModel> getInputListByParams(@Param("productCode") String productCode,
                                                      @Param("type") Integer type,
                                                      @Param("startTime") String startTime,
                                                      @Param("endTime") String endTime);
}
