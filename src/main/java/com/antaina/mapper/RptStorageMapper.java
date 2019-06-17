package com.antaina.mapper;

import com.antaina.entity.storage.RptStorage;
import com.antaina.model.storage.RptStorageQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RptStorageMapper extends Mapper<RptStorage> {
    List<RptStorageQueryModel> getListByParams(@Param("productCode") String productCode, @Param("type") Integer type, @Param("startTime")String startTime, @Param("endTime")String endTime);
}
