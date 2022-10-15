package com.sakuraio.test.service;

import com.sakuraio.nk.auth.api.contract.jwt.JwtSubject;
import com.sakuraio.nk.auth.api.service.JwtSubjectRemoteService;
import com.sakuraio.nk.core.protocol.BaseResponse;
import org.springframework.stereotype.Component;

/**
 * <p>JwtSubjectService</p>
 *
 * @author nekoimi 2022/10/15
 */
@Component
public class JwtSubjectService implements JwtSubjectRemoteService {

    @Override
    public BaseResponse<JwtSubject> loadByIdentifier(String identifier) {
        return BaseResponse.ok(new JwtSubject() {
            @Override
            public String getIdentifier() {
                return "jwtService实现identifier";
            }

            @Override
            public String getUsername() {
                return "jwtService实现username";
            }
        });
    }
}
