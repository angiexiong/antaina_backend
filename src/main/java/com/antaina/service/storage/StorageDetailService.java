package com.antaina.service.storage;

import com.antaina.entity.storage.StorageDetail;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.StorageDetailMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageDetailModel;
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
public class StorageDetailService {

    @Autowired
    private StorageDetailMapper storageDetailMapper;

    public PageInfo getListWithPage(BaseModel baseModel){
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<StorageDetail> storageDetails = storageDetailMapper.selectAll();
        return PageUtil.create(storageDetails);
    }

    public void add(StorageDetailModel storageDetailModel){
        StorageDetail storageDetail = new StorageDetail();
        BeanUtils.copyProperties(storageDetailModel, storageDetail);
        storageDetail.setId(UidUtil.getInstance().nextId());
        storageDetail.setCreateTime(new Date());
        storageDetail.setUpdateTime(new Date());
        storageDetailMapper.insert(storageDetail);
    }

    public void update(StorageDetailModel storageDetailModel){
        if(null == storageDetailModel.getId()){
            throw new BusinessException(MsgResult.USER_ID_EMPTY);
        }
        StorageDetail storageDetail = storageDetailMapper.selectByPrimaryKey(storageDetailModel.getId());
        BeanUtils.copyProperties(storageDetailModel, storageDetail);
        storageDetail.setUpdateTime(new Date());
        storageDetailMapper.updateByPrimaryKeySelective(storageDetail);
    }

    public void delete(Long id){
        if(null != id){
            StorageDetail storageDetail = storageDetailMapper.selectByPrimaryKey(id);
            if(storageDetail != null){
                storageDetailMapper.deleteByPrimaryKey(id);
            }
        }
    }
}
