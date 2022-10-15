package com.sakuraio.test.provider;

import cn.hutool.core.lang.Dict;
import com.sakuraio.nk.auth.api.contract.jwt.JwtSubject;
import com.sakuraio.nk.auth.api.provider.AbstractLoginServiceProvider;
import org.springframework.stereotype.Component;

/**
 * <p>TestLoginProvider</p>
 *
 * @author nekoimi 2022/10/14
 */
@Component
public class TestLoginProvider extends AbstractLoginServiceProvider {
    @Override
    public String getAuthType() {
        return "test";
    }

    @Override
    public JwtSubject doLogin(Dict dict) {
        return new JwtSubject() {
            @Override
            public String getIdentifier() {
                return "test";
            }

            @Override
            public String getUsername() {
                return "test";
            }
        };
    }
}
