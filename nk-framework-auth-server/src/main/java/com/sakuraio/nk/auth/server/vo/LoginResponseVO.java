package com.sakuraio.nk.auth.server.vo;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>LoginResultVO</p>
 *
 * @author nekoimi 2022/10/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@ApiModel
public class LoginResponseVO implements Serializable {
    @ApiModelProperty("权限Token")
    private String token;

    @ApiModelProperty("权限对象信息")
    private JwtSubject subject;
}
