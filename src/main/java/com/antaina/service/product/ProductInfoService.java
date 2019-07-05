package com.antaina.service.product;

import com.antaina.entity.product.ProductInfo;
import com.antaina.entity.storage.RptStorage;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.ProductInfoMapper;
import com.antaina.mapper.RptStorageMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.product.ProductInfoExportModel;
import com.antaina.model.product.ProductInfoModel;
import com.antaina.model.product.ProductInfoQueryModel;
import com.antaina.service.order.OrderInfoService;
import com.antaina.service.storage.StorageInputService;
import com.antaina.service.storage.StorageOutputService;
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
    private OrderInfoService orderInfoService;

    @Autowired
    private StorageInputService storageInputService;

    @Autowired
    private StorageOutputService storageOutputService;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private RptStorageMapper rptStorageMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String customerProductCode, String productCode, String productName, Integer type, Long customerId) {
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<ProductInfoQueryModel> materialInfoList = productInfoMapper.getListByParams(customerProductCode, productCode, productName, type, customerId);
        return PageUtil.create(materialInfoList);
    }

    public void add(ProductInfoModel productInfoModel) {
        ProductInfo condition = new ProductInfo();
        condition.setCustomerProductCode(productInfoModel.getCustomerProductCode());
        List<ProductInfo> productInfoList = productInfoMapper.select(condition);
        if(!CollectionUtils.isEmpty(productInfoList)){
            throw new BusinessException(MsgResult.CUSTOMER_PRODUCT_CODE_EXIST_YES);
        }

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

        ProductInfo condition = new ProductInfo();
        condition.setCustomerProductCode(productInfoModel.getCustomerProductCode());
        List<ProductInfo> productInfoList = productInfoMapper.select(condition);
        if(CollectionUtils.isEmpty(productInfoList)){
            throw new BusinessException(MsgResult.CUSTOMER_PRODUCT_CODE_EXIST_YES);
        }
        BeanUtils.copyProperties(productInfoModel, materialInfo);
        materialInfo.setUpdateTime(new Date());
        productInfoMapper.updateByPrimaryKeySelective(materialInfo);
    }

    public void delete(Long id) {
        if (null != id) {
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(id);
            if (productInfo != null) {
                String customerProductCode = productInfo.getCustomerProductCode();

                // 级联删除其他表与本物料相关的记录，物理删除
                // 暂时需要删除记录的表：order_info + order_delivery_detail，storage_input，storage_output，rpt_storage
                orderInfoService.deleteByCustomerProductCode(customerProductCode);
                storageInputService.deleteByCustomerProductCode(customerProductCode);
                storageOutputService.deleteByCustomerProductCode(customerProductCode);

                RptStorage condition = new RptStorage();
                condition.setCustomerProductCode(customerProductCode);
                rptStorageMapper.delete(condition);

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

    public ProductInfo getProductInfoByCustomerCode(String customerProductCode) {
        ProductInfo pi = new ProductInfo();
        pi.setCustomerProductCode(customerProductCode);
        List<ProductInfo> productInfoList = productInfoMapper.select(pi);
        if (CollectionUtils.isEmpty(productInfoList)) {
            return null;
        }
        return productInfoList.get(0);
    }

    public List<ProductInfoExportModel> exportProduct(String customerProductCode, String productCode, String productName, Integer type, Long customerId) {
        return productInfoMapper.exportProduct(customerProductCode, productCode, productName, type, customerId);
    }
}
