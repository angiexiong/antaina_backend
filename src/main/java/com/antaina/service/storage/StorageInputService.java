package com.antaina.service.storage;

import com.antaina.entity.storage.StorageInfo;
import com.antaina.entity.storage.StorageInput;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.StorageInputMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageInfoModel;
import com.antaina.model.storage.StorageInputModel;
import com.antaina.model.storage.StorageInputQueryModel;
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
public class StorageInputService {

    @Autowired
    private StorageInputMapper storageInputMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String productCode, Integer type, String startTime, String endTime){
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<StorageInputQueryModel> storageInputs = storageInputMapper.getInputListByParams(productCode, type, startTime, endTime);
        return PageUtil.create(storageInputs);
    }

    public void add(StorageInputModel storageInputModel){
        StorageInput storageInput = new StorageInput();
        BeanUtils.copyProperties(storageInputModel, storageInput);
        storageInput.setId(UidUtil.getInstance().nextId());
        storageInput.setCreateTime(new Date());
        storageInput.setUpdateTime(new Date());
        storageInputMapper.insert(storageInput);
    }

    public void update(StorageInputModel storageInputModel){
        if(null == storageInputModel.getId()){
            throw new BusinessException(MsgResult.USER_ID_EMPTY);
        }
        StorageInput storageInput = storageInputMapper.selectByPrimaryKey(storageInputModel.getId());
        BeanUtils.copyProperties(storageInputModel, storageInput);
        storageInput.setUpdateTime(new Date());
        storageInputMapper.updateByPrimaryKeySelective(storageInput);
    }

    public void delete(Long id){
        if(null != id){
            StorageInput storageInput = storageInputMapper.selectByPrimaryKey(id);
            if(storageInput != null){
                storageInputMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
