package com.antaina.controller.storage;

import com.antaina.enums.BaseResult;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageInputExportModel;
import com.antaina.model.storage.StorageInputModel;
import com.antaina.resp.RespBuilder;
import com.antaina.service.storage.StorageInputService;
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
@RequestMapping(value = "/api/input")
@Api(description = "【入库接口】")
public class StorageInputController {

    @Autowired
    private StorageInputService storageInputService;

    @ApiOperation(value = "查询")
    @GetMapping("/getListWithPage")
    public ResponseEntity getListWithPage(BaseModel baseModel, String customerProductCode, Integer type, String startTime, String endTime){
        PageInfo page = storageInputService.getListWithPage(baseModel, customerProductCode, type, startTime, endTime);
        return RespBuilder.build(BaseResult.SUCCESS, page);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody StorageInputModel storageInputModel){
        storageInputService.add(storageInputModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody StorageInputModel storageInputModel){
        storageInputService.update(storageInputModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResponseEntity delete(Long id){
        storageInputService.delete(id);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "导出")
    @GetMapping("/exportInput")
    public ResponseEntity exportOutput(BaseModel baseModel, String customerProductCode, Integer type, String startTime, String endTime){
        List<StorageInputExportModel> list = storageInputService.exportOutput(customerProductCode, type, startTime, endTime);
        return RespBuilder.build(BaseResult.SUCCESS, list);
    }
}
