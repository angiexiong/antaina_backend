package com.antaina.controller.customer;

import com.antaina.entity.customer.CustomerInfo;
import com.antaina.enums.BaseResult;
import com.antaina.model.BaseModel;
import com.antaina.model.customer.CustomerInfoModel;
import com.antaina.resp.RespBuilder;
import com.antaina.service.customer.CustomerInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/api/customer")
@Api(description = "【客户管理接口】")
public class CustomerController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @ApiOperation(value = "查询")
    @GetMapping("/getListWithPage")
    public ResponseEntity getListWithPage(BaseModel baseModel, String name){
        PageInfo page = customerInfoService.getListWithPage(baseModel, name);
        return RespBuilder.build(BaseResult.SUCCESS, page);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CustomerInfoModel customerInfoModel){
        customerInfoService.add(customerInfoModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody CustomerInfoModel customerInfoModel){
        customerInfoService.update(customerInfoModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResponseEntity delete(Long id){
        customerInfoService.delete(id);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "不分页查询")
    @GetMapping("/getCustomerList")
    public ResponseEntity getList(){
        List<CustomerInfo> list = customerInfoService.getList();
        return RespBuilder.build(BaseResult.SUCCESS, list);
    }

    @ApiOperation(value = "根据id获取客户信息")
    @GetMapping("/getById")
    public ResponseEntity getById(Long id){
        CustomerInfo customerInfo = customerInfoService.getById(id);
        return RespBuilder.build(BaseResult.SUCCESS, customerInfo);
    }
}
