package com.antaina.service.customer;

import com.antaina.entity.customer.CustomerInfo;
import com.antaina.enums.MsgResult;
import com.antaina.exception.BusinessException;
import com.antaina.mapper.CustomerInfoMapper;
import com.antaina.model.BaseModel;
import com.antaina.model.customer.CustomerInfoModel;
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
public class CustomerInfoService {

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    public PageInfo getListWithPage(BaseModel baseModel, String name){
        PageHelper.startPage(baseModel.getPageNum(), baseModel.getPageSize());
        List<CustomerInfo> customerList = customerInfoMapper.getCustomerListByParams(name);
        return PageUtil.create(customerList);
    }

    public void add(CustomerInfoModel customerInfoModel){
        CustomerInfo customerInfo = new CustomerInfo();
        BeanUtils.copyProperties(customerInfoModel, customerInfo);
        customerInfo.setId(UidUtil.getInstance().nextId());
        customerInfo.setCreateTime(new Date());
        customerInfo.setUpdateTime(new Date());
        customerInfoMapper.insert(customerInfo);
    }

    public void update(CustomerInfoModel customerInfoModel){
        if(null == customerInfoModel.getId()){
            throw new BusinessException(MsgResult.USER_ID_EMPTY);
        }
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerInfoModel.getId());
        BeanUtils.copyProperties(customerInfoModel, customerInfo);
        customerInfo.setUpdateTime(new Date());
        customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
    }

    public void delete(Long id){
        if(null != id){
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(id);
            if(customerInfo != null){
                customerInfoMapper.deleteByPrimaryKey(id);
            }
        }
    }

    public List<CustomerInfo> getList() {
        return customerInfoMapper.getCustomerListByParams(null);
    }

    public CustomerInfo getById(Long id) {
        return customerInfoMapper.selectByPrimaryKey(id);
    }
}
