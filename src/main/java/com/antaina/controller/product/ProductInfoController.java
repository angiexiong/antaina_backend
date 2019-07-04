package com.antaina.controller.product;

import com.antaina.entity.product.ProductInfo;
import com.antaina.enums.BaseResult;
import com.antaina.model.BaseModel;
import com.antaina.model.product.ProductInfoExportModel;
import com.antaina.model.product.ProductInfoModel;
import com.antaina.resp.RespBuilder;
import com.antaina.service.product.ProductInfoService;
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
@RequestMapping(value = "/api/product")
@Api(description = "【物料管理接口】")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productInfoService;

    @ApiOperation(value = "查询")
    @GetMapping("/getListWithPage")
    public ResponseEntity getListWithPage(BaseModel baseModel, String customerProductCode, String productCode, String productName, Integer type, Long customerId){
        PageInfo page = productInfoService.getListWithPage(baseModel, customerProductCode, productCode, productName, type, customerId);
        return RespBuilder.build(BaseResult.SUCCESS, page);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ProductInfoModel productInfoModel){
        productInfoService.add(productInfoModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody ProductInfoModel productInfoModel){
        productInfoService.update(productInfoModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResponseEntity delete(Long id){
        productInfoService.delete(id);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "根据id获取物料信息")
    @GetMapping("/getById")
    public ResponseEntity getById(Long id){
        ProductInfo productInfo = productInfoService.getById(id);
        return RespBuilder.build(BaseResult.SUCCESS, productInfo);
    }

    @ApiOperation(value = "根据id获取物料信息")
    @GetMapping("/getProductList")
    public ResponseEntity getProductList(){
        List<ProductInfo> list = productInfoService.getProductList();
        return RespBuilder.build(BaseResult.SUCCESS, list);
    }

    @ApiOperation(value = "导出")
    @GetMapping("/exportProduct")
    public ResponseEntity exportProduct(BaseModel baseModel, String customerProductCode, String productCode, String productName, Integer type, Long customerId){
        List<ProductInfoExportModel> list = productInfoService.exportProduct(customerProductCode, productCode, productName, type, customerId);
        return RespBuilder.build(BaseResult.SUCCESS, list);
    }
}
