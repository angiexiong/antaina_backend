package com.antaina.controller.order;

import com.antaina.entity.customer.CustomerInfo;
import com.antaina.entity.order.OrderInfo;
import com.antaina.enums.BaseResult;
import com.antaina.model.BaseModel;
import com.antaina.model.order.OrderInfoModel;
import com.antaina.resp.RespBuilder;
import com.antaina.service.order.OrderInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/api/order")
@Api(description = "【订单管理接口】")
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation(value = "查询")
    @GetMapping("/getListWithPage")
    public ResponseEntity getListWithPage(BaseModel baseModel, String customerProductCode, String productCode, Integer status, String startTime, String endTime){
        PageInfo page = orderInfoService.getListWithPage(baseModel,customerProductCode,productCode,status, startTime, endTime);
        return RespBuilder.build(BaseResult.SUCCESS, page);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody OrderInfoModel orderInfoModel){
        orderInfoService.add(orderInfoModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody OrderInfoModel orderInfoModel){
        orderInfoService.update(orderInfoModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResponseEntity delete(Long id){
        orderInfoService.delete(id);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "根据id获取订单信息")
    @GetMapping("/getById")
    public ResponseEntity getById(Long id){
        OrderInfo orderInfo = orderInfoService.getById(id);
        return RespBuilder.build(BaseResult.SUCCESS, orderInfo);
    }
}
