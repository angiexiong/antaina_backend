package com.antaina.mapper;

import com.antaina.entity.storage.StorageOutput;
import com.antaina.model.storage.StorageInputOutputQueryModel;
import com.antaina.model.storage.StorageOutputExportModel;
import com.antaina.model.storage.StorageOutputQueryModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface StorageOutputMapper extends Mapper<StorageOutput> {

    List<StorageOutputQueryModel> getInputListByParams(@Param("productCode") String productCode,
                                                       @Param("orderNo") String orderNo,
                                                       @Param("type") Integer type,
                                                       @Param("startTime") String startTime,
                                                       @Param("endTime") String endTime);

    List<StorageInputOutputQueryModel> getListByStatisticFlag();

    int batchUpdateStatisticFlag(@Param("ids") List<Long> ids);

    List<StorageOutputExportModel> getExportOutputList(@Param("productCode") String productCode,
                                                       @Param("orderNo") String orderNo,
                                                       @Param("type") Integer type,
                                                       @Param("startTime") String startTime,
                                                       @Param("endTime") String endTime);
}
