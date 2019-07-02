package com.antaina.service.storage;

import com.antaina.entity.product.ProductInfo;
import com.antaina.entity.storage.StorageInput;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.ProductInfoMapper;
import com.antaina.mapper.StorageInputMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageInputModel;
import com.antaina.model.storage.StorageInputQueryModel;
import com.antaina.service.product.ProductInfoService;
import com.antaina.util.PageUtil;
import com.antaina.util.UidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class StorageInputService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private StorageInputMapper storageInputMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String productCode, Integer type, String startTime, String endTime){
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<StorageInputQueryModel> storageInputs = storageInputMapper.getInputListByParams(productCode, type, startTime, endTime);
        return PageUtil.create(storageInputs);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(StorageInputModel storageInputModel){

        ProductInfo productInfo = productInfoService.getProductInfoByCode(storageInputModel.getProductCode());
        if(null == productInfo){
            throw new BusinessException(MsgResult.PRODUCT_INFO_EXIST_NO);
        }
        productInfo.setTotalAmount(productInfo.getTotalAmount().add(storageInputModel.getAmount()));
        productInfo.setUpdateTime(new Date());
        productInfoMapper.updateByPrimaryKey(productInfo);

        StorageInput storageInput = new StorageInput();
        BeanUtils.copyProperties(storageInputModel, storageInput);
        storageInput.setId(UidUtil.getInstance().nextId());
        storageInput.setStatisticFlag(0);
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

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        if(null != id){
            StorageInput storageInput = storageInputMapper.selectByPrimaryKey(id);
            if(storageInput != null){
                ProductInfo productInfo = productInfoService.getProductInfoByCode(storageInput.getProductCode());
                if(null == productInfo){
                    throw new BusinessException(MsgResult.PRODUCT_INFO_EXIST_NO);
                }
                productInfo.setTotalAmount(productInfo.getTotalAmount().subtract(storageInput.getAmount()));
                productInfo.setUpdateTime(new Date());
                productInfoMapper.updateByPrimaryKey(productInfo);
                storageInputMapper.deleteByPrimaryKey(id);
            }
        }
    }

    /**
     * 本接口仅供删除物料信息时删除进出库时使用
     * @param productCode
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByProductCode(String productCode){
        StorageInput condition = new StorageInput();
        condition.setProductCode(productCode);
        storageInputMapper.delete(condition);
    }
}
