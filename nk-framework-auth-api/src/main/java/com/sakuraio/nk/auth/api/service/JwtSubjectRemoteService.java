package com.sakuraio.nk.auth.api.service;

import com.sakuraio.nk.auth.api.contract.jwt.JwtSubject;
import com.sakuraio.nk.core.protocol.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>JwtSubjectRemoteService</p>
 * <p>
 * 认证对象接口，根据需求导入不同实现
 *
 * @author nekoimi 2022/10/15
 */
public interface JwtSubjectRemoteService {

    @RequestMapping("/api/auth/loadByIdentifier")
    BaseResponse<JwtSubject> loadByIdentifier(String identifier);
}
