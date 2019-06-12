package com.antaina.model.admin;

import com.antaina.constants.ConstantsCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lp
 * @date: 2018/11/13 16:49
 * @description: 后台管理员账号修改密码model
 */
@Data
public class UpdatePwdModel {

    @NotNull(message = ConstantsCode.ID_EMPTY + "")
    @ApiModelProperty(value = "id", required = true, example = "1234545")
    private Long id;

    @NotBlank(message = ConstantsCode.PASS_EMPTY + "")
    @ApiModelProperty(value = "oldPwd", required = true, example = "1234545")
    private String oldPwd;

    @NotBlank(message = ConstantsCode.PASS_EMPTY + "")
    @ApiModelProperty(value = "newPwd", required = true, example = "1234545")
    private String newPwd;

    @NotBlank(message = ConstantsCode.PASS_EMPTY + "")
    @ApiModelProperty(value = "confirmPwd", required = true, example = "1234545")
    private String confirmPwd;

}
