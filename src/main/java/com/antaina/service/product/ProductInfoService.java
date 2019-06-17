package com.antaina.service.product;

import com.antaina.entity.product.ProductInfo;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.ProductInfoMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.product.ProductInfoModel;
import com.antaina.model.product.ProductInfoQueryModel;
import com.antaina.util.PageUtil;
import com.antaina.util.UidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String productCode, String productName, Integer type, Long customerId) {
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<ProductInfoQueryModel> materialInfoList = productInfoMapper.getListByParams(productCode, productName, type, customerId);
        return PageUtil.create(materialInfoList);
    }

    public void add(ProductInfoModel productInfoModel) {
        ProductInfo materialInfo = new ProductInfo();
        BeanUtils.copyProperties(productInfoModel, materialInfo);
        materialInfo.setId(UidUtil.getInstance().nextId());
        materialInfo.setCreateTime(new Date());
        materialInfo.setUpdateTime(new Date());
        productInfoMapper.insert(materialInfo);
    }

    public void update(ProductInfoModel productInfoModel) {
        if (null == productInfoModel.getId()) {
            throw new BusinessException(MsgResult.PRODUCT_ID_EMPTY);
        }
        ProductInfo materialInfo = productInfoMapper.selectByPrimaryKey(productInfoModel.getId());
        BeanUtils.copyProperties(productInfoModel, materialInfo);
        materialInfo.setUpdateTime(new Date());
        productInfoMapper.updateByPrimaryKeySelective(materialInfo);
    }

    public void delete(Long id) {
        if (null != id) {
            ProductInfo materialInfo = productInfoMapper.selectByPrimaryKey(id);
            if (materialInfo != null) {
                productInfoMapper.deleteByPrimaryKey(id);
            }
        }
    }

    public ProductInfo getById(Long id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    public List<ProductInfo> getProductList() {
        return productInfoMapper.selectAll();
    }

    public ProductInfo getProductInfoByCode(String productCode){
        ProductInfo pi = new ProductInfo();
        pi.setProductCode(productCode);
        List<ProductInfo> productInfoList = productInfoMapper.select(pi);
        if(CollectionUtils.isEmpty(productInfoList)){
            return null;
        }
        return productInfoList.get(0);
    }
}
