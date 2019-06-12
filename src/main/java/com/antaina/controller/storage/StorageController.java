package com.antaina.controller.storage;

import com.antaina.enums.BaseResult;
import com.antaina.model.BaseModel;
import com.antaina.model.storage.StorageInfoModel;
import com.antaina.resp.RespBuilder;
import com.antaina.service.storage.StorageInfoService;
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
@RequestMapping(value = "/api/storage")
@Api(description = "【库存管理接口】")
public class StorageController {

    @Autowired
    private StorageInfoService storageInfoService;

    @ApiOperation(value = "查询")
    @GetMapping("/getListWithPage")
    public ResponseEntity getListWithPage(BaseModel baseModel){
        PageInfo page = storageInfoService.getListWithPage(baseModel);
        return RespBuilder.build(BaseResult.SUCCESS, page);
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody StorageInfoModel storageInfoModel){
        storageInfoService.add(storageInfoModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody StorageInfoModel storageInfoModel){
        storageInfoService.update(storageInfoModel);
        return RespBuilder.build(BaseResult.SUCCESS);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResponseEntity delete(Long id){
        storageInfoService.delete(id);
        return RespBuilder.build(BaseResult.SUCCESS);
    }
}
