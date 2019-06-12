package com.antaina.service.storage;

import com.antaina.entity.storage.StorageInfo;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.StorageInfoMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageInfoModel;
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
public class StorageInfoService {

    @Autowired
    private StorageInfoMapper storageInfoMapper;

    public PageInfo getListWithPage(BaseModel baseModel){
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<StorageInfo> customerInfos = storageInfoMapper.selectAll();
        return PageUtil.create(customerInfos);
    }

    public void add(StorageInfoModel storageInfoModel){
        StorageInfo storageInfo = new StorageInfo();
        BeanUtils.copyProperties(storageInfoModel, storageInfo);
        storageInfo.setId(UidUtil.getInstance().nextId());
        storageInfo.setCreateTime(new Date());
        storageInfo.setUpdateTime(new Date());
        storageInfoMapper.insert(storageInfo);
    }

    public void update(StorageInfoModel storageInfoModel){
        if(null == storageInfoModel.getId()){
            throw new BusinessException(MsgResult.USER_ID_EMPTY);
        }
        StorageInfo storageInfo = storageInfoMapper.selectByPrimaryKey(storageInfoModel.getId());
        BeanUtils.copyProperties(storageInfoModel, storageInfo);
        storageInfo.setUpdateTime(new Date());
        storageInfoMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public void delete(Long id){
        if(null != id){
            StorageInfo materialInfo = storageInfoMapper.selectByPrimaryKey(id);
            if(materialInfo != null){
                storageInfoMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
