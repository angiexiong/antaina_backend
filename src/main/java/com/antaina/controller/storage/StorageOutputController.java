package com.antaina.controller.storage;

import com.antaina.enums.BaseResult;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageOutputModel;
import com.antaina.resp.RespBuilder;
import com.antaina.service.storage.StorageOutputService;
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
@RequestMapping(value = "/api/output")
@Api(description = "【出库接口】")
public class StorageOutputController {

    @Autowired
    private StorageOutputService storageOutputService;

    @ApiOperation(value = "查询")
    @GetMapping("/getListWithPage")
    public ResponseEntity getListWithPage(BaseModel baseModel, String productCode, Integer type, String startTime, String endTime){
        PageInfo page = storageOutputService.getListWithPage(baseModel, productCode, type, startTime, endTime);
        return RespBuilder.build(BaseResult.SUCCESS, page);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody StorageOutputModel storageOutputModel){
        storageOutputService.add(storageOutputModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody StorageOutputModel storageOutputModel){
        storageOutputService.update(storageOutputModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResponseEntity delete(Long id){
        storageOutputService.delete(id);
        return RespBuilder.build(BaseResult.SUCCESS);
    }
}