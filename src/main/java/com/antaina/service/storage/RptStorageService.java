package com.antaina.service.storage;

import com.antaina.mapper.RptStorageMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.RptStorageQueryModel;
import com.antaina.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RptStorageService {

    @Autowired
    private RptStorageMapper rptStorageMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String productCode, Integer type, String startTime, String endTime) {
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<RptStorageQueryModel> rptStorageList = rptStorageMapper.getListByParams(productCode, type, startTime, endTime);
        return PageUtil.create(rptStorageList);
    }
}
