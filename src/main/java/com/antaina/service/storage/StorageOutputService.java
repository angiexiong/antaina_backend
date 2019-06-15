package com.antaina.service.storage;

import com.antaina.entity.storage.StorageInfo;
import com.antaina.entity.storage.StorageOutput;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.StorageInfoMapper;
import com.antaina.mapper.StorageOutputMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageInfoModel;
import com.antaina.model.storage.StorageOutputModel;
import com.antaina.model.storage.StorageOutputQueryModel;
import com.antaina.util.PageUtil;
import com.antaina.util.UidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StorageOutputService {

    @Autowired
    private StorageOutputMapper storageOutputMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String productCode, Integer type, String startTime, String endTime){
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<StorageOutputQueryModel> storageOutputList = storageOutputMapper.getInputListByParams(productCode, type, startTime, endTime);
        return PageUtil.create(storageOutputList);
    }

    public void add(StorageOutputModel storageOutputModel){
        StorageOutput storageOutput = new StorageOutput();
        BeanUtils.copyProperties(storageOutputModel, storageOutput);
        storageOutput.setId(UidUtil.getInstance().nextId());
        storageOutput.setCreateTime(new Date());
        storageOutput.setUpdateTime(new Date());
        storageOutputMapper.insert(storageOutput);
    }

    public void update(StorageOutputModel storageOutputModel){
        if(null == storageOutputModel.getId()){
            throw new BusinessException(MsgResult.USER_ID_EMPTY);
        }
        StorageOutput storageOutput = storageOutputMapper.selectByPrimaryKey(storageOutputModel.getId());
        BeanUtils.copyProperties(storageOutputModel, storageOutput);
        storageOutput.setUpdateTime(new Date());
        storageOutputMapper.updateByPrimaryKeySelective(storageOutput);
    }

    public void delete(Long id){
        if(null != id){
            StorageOutput storageOutput = storageOutputMapper.selectByPrimaryKey(id);
            if(storageOutput != null){
                storageOutputMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
