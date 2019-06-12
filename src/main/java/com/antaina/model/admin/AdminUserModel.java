package com.antaina.model.admin;

import com.antaina.constants.ConstantsCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminUserModel {

    @ApiModelProperty(value = "用户id(更新时必填)", required = true, example = "535492994632151040")
    private Long id;

    @NotBlank(message = ConstantsCode.USER_ACCOUNT_EMPTY + "")
    @ApiModelProperty(value = "用户账号", required = true, example = "15974287688")
    private String useraccount;

    @NotBlank(message = ConstantsCode.PASS_EMPTY + "")
    @ApiModelProperty(value = "登陆密码", required = true, example = "123456")
    private String password;

    @NotBlank(message = ConstantsCode.USERNAME_EMPTY + "")
    @ApiModelProperty(value = "用户名", required = true, example = "不二微笑")
    private String username;

    @ApiModelProperty(value = "联系电话", example = "15987657680")
    private String phone;

    @ApiModelProperty(value = "用户访问权限", example = "message;support;article...(用;分隔)")
    private String accessPermission;

    @ApiModelProperty(value = "用户状态", example = "状态（0:删除,1:正常,2冻结登陆）")
    private Integer status;

    @ApiModelProperty(value = "用户访问菜单", example = "管理员列表;交易列表;管理列表...(用;分隔)")
    private String accessMenu;
}
