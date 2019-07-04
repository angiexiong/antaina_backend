package com.antaina.mapper;

import com.antaina.entity.storage.StorageInput;
import com.antaina.model.storage.StorageInputExportModel;
import com.antaina.model.storage.StorageInputOutputQueryModel;
import com.antaina.model.storage.StorageInputQueryModel;
import com.antaina.model.storage.StorageOutputExportModel;
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

    List<StorageInputOutputQueryModel> getListByStatisticFlag();

    int batchUpdateStatisticFlag(@Param("ids") List<Long> ids);

    List<StorageInputExportModel> getExportInputList(@Param("productCode") String productCode,
                                                     @Param("orderNo") String orderNo,
                                                     @Param("type") Integer type,
                                                     @Param("startTime") String startTime,
                                                     @Param("endTime") String endTime);
}
