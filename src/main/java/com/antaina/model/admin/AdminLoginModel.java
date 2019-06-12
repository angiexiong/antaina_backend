package com.antaina.model.admin;

import com.antaina.constants.ConstantsCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lp
 * @date: 2018/11/14 15:03
 * @description: 后台登陆model
 */
@Data
public class AdminLoginModel {

    @NotBlank(message = ConstantsCode.USER_ACCOUNT_EMPTY + "")
    @ApiModelProperty(value = "useraccount", required = true, example = "admin")
    private String useraccount;

    @NotBlank(message = ConstantsCode.PASS_EMPTY + "")
    @ApiModelProperty(value = "password", required = true, example = "admin")
    private String password;

}
