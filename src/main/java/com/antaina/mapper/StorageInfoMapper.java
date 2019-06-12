package com.antaina.mapper;

import com.antaina.entity.storage.StorageInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface StorageInfoMapper extends Mapper<StorageInfo> {
}
