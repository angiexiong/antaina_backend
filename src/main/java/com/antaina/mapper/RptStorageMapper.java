package com.antaina.mapper;

import com.antaina.entity.storage.RptStorage;
import com.antaina.model.storage.RptStorageExportModel;
import com.antaina.model.storage.RptStorageQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RptStorageMapper extends Mapper<RptStorage> {
    List<RptStorageQueryModel> getListByParams(@Param("customerProductCode") String customerProductCode,
                                               @Param("type") Integer type,
                                               @Param("startTime")String startTime,
                                               @Param("endTime")String endTime);

    int batchInsert(@Param("list") List<RptStorage> rptUserList);

    List<RptStorage> getListByTimeInterval(@Param("frequencyType") Integer type,
                                           @Param("timeInterval")Integer timeInterval,
                                           @Param("timeUnit")String timeUnit);

    List<RptStorageExportModel> exportRptStorage(@Param("customerProductCode") String customerProductCode,
                                                 @Param("type") Integer type,
                                                 @Param("startTime")String startTime,
                                                 @Param("endTime")String endTime);
}
