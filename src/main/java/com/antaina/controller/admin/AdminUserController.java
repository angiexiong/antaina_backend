package com.antaina.controller.admin;

import com.antaina.entity.admin.AdminUser;
import com.antaina.enums.BaseResult;
import com.antaina.exception.ParameterCheckException;
import com.antaina.model.BaseModel;
import com.antaina.model.LoginSuccessModel;
import com.antaina.model.admin.AdminLoginModel;
import com.antaina.model.admin.AdminUserModel;
import com.antaina.model.admin.UpdatePwdModel;
import com.antaina.resp.Message;
import com.antaina.resp.RespBuilder;
import com.antaina.service.admin.AdminUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(value = "/api/admin")
@Api(description = "【管理员接口】")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 后台账号登陆
     *
     * @return
     */
    @ApiOperation(value = "后台账号登陆")
    @PostMapping(value = "/adminLogin")
    public ResponseEntity adminLogin(@Valid @RequestBody AdminLoginModel alm, BindingResult bindingResult) throws ParameterCheckException {
        log.info("进入接口 -> 后台账号登陆, {}", alm);
        if (bindingResult.hasErrors()) {
            throw new ParameterCheckException(bindingResult);
        }
        LoginSuccessModel loginSuccessModel = adminUserService.adminLogin(alm);
        log.info("结束接口 -> 后台账号登陆, {}", loginSuccessModel);
        return RespBuilder.build(BaseResult.SUCCESS, loginSuccessModel);
    }

    /**
     * 添加后台管理账号
     *
     * @param aum
     * @param bindingResult
     * @return
     * @throws ParameterCheckException
     */
    @ApiOperation(value = "添加后台管理账号")
    @PostMapping(value = "/add")
    public ResponseEntity add(@Valid @RequestBody AdminUserModel aum, BindingResult bindingResult) throws ParameterCheckException {
        log.info("进入接口 -> 添加后台管理账号, {}", aum);
        if (bindingResult.hasErrors()) {
            throw new ParameterCheckException(bindingResult);
        }
        Message msg = adminUserService.add(aum);
        log.info("结束接口 -> 添加后台管理账号, {}", msg);
        return RespBuilder.build(msg);
    }

    /**
     * 修改密码
     *
     * @param upm
     * @param bindingResult
     * @return
     * @throws ParameterCheckException
     */
    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/updatePassword")
    public ResponseEntity updatePassword(@Valid @RequestBody UpdatePwdModel upm, BindingResult bindingResult) throws ParameterCheckException {
        log.info("进入接口 -> 修改密码, {}", upm);
        if (bindingResult.hasErrors()) {
            throw new ParameterCheckException(bindingResult);
        }
        Message msg = adminUserService.updatePassword(upm);
        log.info("结束接口 -> 修改密码, {}", msg);
        return RespBuilder.build(msg);
    }

    /**
     * 管理账号列表查询
     *
     * @param baseModel
     * @return
     */
    @ApiOperation(value = "管理账号列表查询", response = AdminUser.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, dataType = "int", paramType = "query", defaultValue = "20")
    })
    @GetMapping(value = "/list")
    public ResponseEntity list(@RequestHeader("Access-Token") String token, BaseModel baseModel, String startTime, String endTime) {
        log.info("进入接口 -> 管理账号列表查询");
        PageInfo page = adminUserService.list(token, baseModel, startTime, endTime);
        log.info("结束接口 -> 管理账号列表查询, {}", page);
        return RespBuilder.build(BaseResult.SUCCESS, page);
    }

//    @ApiOperation(value = "导出", response = AdminUser.class)
//    @GetMapping(value = "/export")
//    public ResponseEntity export(@RequestHeader("Access-Token") String token, String startTime, String endTime) {
//        log.info("进入接口 -> 导出, token:{}", token);
//        List<AdminUser> page = adminUserService.export(token, startTime, endTime);
//        log.info("结束接口 -> 导出, {}", page);
//        return RespBuilder.build(BaseResult.SUCCESS, page);
//    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询", response = AdminUser.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "Long", paramType = "query", defaultValue = "234242342423")
    })
    @GetMapping(value = "/getById")
    public ResponseEntity getById(Long id) {
        log.info("进入接口 -> 根据id查询, {}", id);
        AdminUser adminUser = adminUserService.getById(id);
        log.info("结束接口 -> 根据id查询, {}", adminUser);
        return RespBuilder.build(BaseResult.SUCCESS, adminUser);
    }

    /**
     * 管理员修改某个账号
     *
     * @param adminUserModel
     * @return
     */
    @ApiOperation(value = "管理员修改某个账号")
    @PostMapping(value = "/update")
    public ResponseEntity update(@Valid @RequestBody AdminUserModel adminUserModel) {
        log.info("进入接口 -> 管理员修改某个账号, params={}", adminUserModel);
        int result = adminUserService.update( adminUserModel);
        log.info("结束接口 -> 管理员修改某个账号, {}", result);
        return RespBuilder.build(BaseResult.SUCCESS, result);
    }

    /**
     * 【后台管理端】根据token查询用户信息
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "根据token查询用户信息", response = AdminUser.class)
    @GetMapping(value = "/getByToken")
    public ResponseEntity getByToken(@RequestHeader("Access-Token") String token) {
        log.info("进入接口 -> 根据token查询用户信息, {}", token);
        AdminUser adminUser = adminUserService.getByToken(token);
        log.info("结束接口 -> 根据token查询用户信息, {}", adminUser);
        return RespBuilder.build(BaseResult.SUCCESS, adminUser);
    }

    @ApiOperation(value = "管理员退出", response = Boolean.class)
    @GetMapping(value = "/adminLogout")
    public ResponseEntity adminLogOut(@RequestHeader("Access-Token") String token) {
        log.info("进入接口 -> 根据token查询用户信息, {}", token);
        boolean result = adminUserService.adminLogOut(token);
        log.info("结束接口 -> 根据token查询用户信息, {}", result);
        return RespBuilder.build(BaseResult.SUCCESS, result);
    }
}
