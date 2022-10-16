package com.sakuraio.test.controller;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.sso.SsoUtils;
import com.sakuraio.nk.core.protocol.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>TestController</p>
 *
 * @author nekoimi 2022/10/16
 */
@RestController
public class TestController {

    @GetMapping("/api/user")
    public BaseResponse<JwtSubject> getUser() {
        return BaseResponse.ok(SsoUtils.getSubject());
    }
}
