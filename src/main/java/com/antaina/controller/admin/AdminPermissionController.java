package com.antaina.controller.admin;

import com.antaina.resp.Message;
import com.antaina.resp.RespBuilder;
import com.antaina.service.admin.AdminPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/api/admin/permission")
@Api(description = "【管理员权限接口】")
public class AdminPermissionController {

    @Autowired
    private AdminPermissionService adminPermissionService;

    /**
     * 权限列表查询
     *
     * @return
     */
    @ApiOperation(value = "权限列表查询")
    @GetMapping(value = "/list")
    public ResponseEntity list() {
        log.info("进入接口 -> 权限列表查询, {}");
        Message msg = adminPermissionService.list();
        log.info("结束接口 -> 权限列表查询, {}", msg);
        return RespBuilder.build(msg);
    }

}
