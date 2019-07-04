package com.antaina.service.storage;

import com.antaina.entity.order.OrderInfo;
import com.antaina.entity.product.ProductInfo;
import com.antaina.entity.storage.StorageOutput;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.OrderInfoMapper;
import com.antaina.mapper.ProductInfoMapper;
import com.antaina.mapper.StorageOutputMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageOutputExportModel;
import com.antaina.model.storage.StorageOutputModel;
import com.antaina.model.storage.StorageOutputQueryModel;
import com.antaina.service.product.ProductInfoService;
import com.antaina.util.PageUtil;
import com.antaina.util.UidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class StorageOutputService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private StorageOutputMapper storageOutputMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String productCode, String orderNo, Integer type, String startTime, String endTime){
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<StorageOutputQueryModel> storageOutputList = storageOutputMapper.getInputListByParams(productCode, orderNo, type, startTime, endTime);
        return PageUtil.create(storageOutputList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(StorageOutputModel storageOutputModel){

        ProductInfo productInfo = productInfoService.getProductInfoByCode(storageOutputModel.getProductCode());
        if(null == productInfo){
            throw new BusinessException(MsgResult.PRODUCT_INFO_EXIST_NO);
        }
        productInfo.setTotalAmount(productInfo.getTotalAmount().subtract(storageOutputModel.getAmount()));
        productInfo.setUpdateTime(new Date());
        productInfoMapper.updateByPrimaryKey(productInfo);

        // 订单号不为空，则需要校验是否有这个订单号
        if(StringUtils.isNotBlank(storageOutputModel.getOrderNo())){
            OrderInfo condition = new OrderInfo();
            condition.setOrderNo(storageOutputModel.getOrderNo());
            List<OrderInfo> orderInfoList = orderInfoMapper.select(condition);
            if(CollectionUtils.isEmpty(orderInfoList)){
                throw new BusinessException(MsgResult.ORDER_INFO_EXIST_NO);
            }
        }

        StorageOutput storageOutput = new StorageOutput();
        BeanUtils.copyProperties(storageOutputModel, storageOutput);
        storageOutput.setId(UidUtil.getInstance().nextId());
        storageOutput.setStatisticFlag(0);
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

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        if(null != id){
            StorageOutput storageOutput = storageOutputMapper.selectByPrimaryKey(id);
            if(storageOutput != null){
                ProductInfo productInfo = productInfoService.getProductInfoByCode(storageOutput.getProductCode());
                if(null == productInfo){
                    throw new BusinessException(MsgResult.PRODUCT_INFO_EXIST_NO);
                }
                productInfo.setTotalAmount(productInfo.getTotalAmount().add(storageOutput.getAmount()));
                productInfo.setUpdateTime(new Date());
                productInfoMapper.updateByPrimaryKey(productInfo);
                storageOutputMapper.deleteByPrimaryKey(id);
            }
        }
    }

    /**
     * 本接口仅供删除物料信息时删除进出库时使用
     * @param productCode
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByProductCode(String productCode){
        StorageOutput condition = new StorageOutput();
        condition.setProductCode(productCode);
        storageOutputMapper.delete(condition);
    }

    public List<StorageOutputExportModel> exportOutput(String productCode, String orderNo, Integer type, String startTime, String endTime) {
        return storageOutputMapper.getExportOutputList(productCode, orderNo, type, startTime, endTime);
    }
}
