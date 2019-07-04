package com.antaina.controller.storage;

import com.antaina.enums.BaseResult;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.RptStorageExportModel;
import com.antaina.resp.RespBuilder;
import com.antaina.service.storage.RptStorageService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/api/storage")
@Api(description = "【库存管理接口】")
public class RptStorageController {

    @Autowired
    private RptStorageService rptStorageService;

    @ApiOperation(value = "查询")
    @GetMapping("/getListWithPage")
    public ResponseEntity getListWithPage(BaseModel baseModel, String productCode, Integer type, String startTime, String endTime) {
        PageInfo page = rptStorageService.getListWithPage(baseModel, productCode, type, startTime, endTime);
        return RespBuilder.build(BaseResult.SUCCESS, page);
    }

    @ApiOperation(value = "导出")
    @GetMapping("/exportRptStorage")
    public ResponseEntity exportRptStorage(BaseModel baseModel, String productCode, Integer type, String startTime, String endTime) {
        List<RptStorageExportModel> list = rptStorageService.exportRptStorage(productCode, type, startTime, endTime);
        return RespBuilder.build(BaseResult.SUCCESS, list);
    }
}
