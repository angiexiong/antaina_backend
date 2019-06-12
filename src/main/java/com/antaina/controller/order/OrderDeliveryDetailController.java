package com.antaina.controller.order;

import com.antaina.entity.order.OrderDeliveryDetail;
import com.antaina.enums.BaseResult;
import com.antaina.model.order.OrderDeliveryDetailModel;
import com.antaina.resp.RespBuilder;
import com.antaina.service.order.OrderDeliveryDetailService;
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
@RequestMapping(value = "/api/delivery")
@Api(description = "【订单出货详情接口】")
public class OrderDeliveryDetailController {

    @Autowired
    private OrderDeliveryDetailService orderDeliveryDetailService;

    @ApiOperation(value="查询订单出货明细")
    @PostMapping("/getDeliveryListByOrderId")
    public ResponseEntity getDeliveryListByOrderId(Long orderId){
        List<OrderDeliveryDetail> list = orderDeliveryDetailService.getDeliveryListByOrderId(orderId);
        return RespBuilder.build(BaseResult.SUCCESS, list);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody OrderDeliveryDetailModel orderDeliveryDetailModel){
        orderDeliveryDetailService.add(orderDeliveryDetailModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResponseEntity delete(Long deliveryId){
        orderDeliveryDetailService.delete(deliveryId);
        return RespBuilder.build(BaseResult.SUCCESS);
    }
}
