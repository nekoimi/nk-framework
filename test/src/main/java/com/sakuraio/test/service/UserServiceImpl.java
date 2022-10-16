package com.sakuraio.test.service;

import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.server.service.JwtSubjectService;
import com.sakuraio.test.entity.User;
import org.springframework.stereotype.Service;

/**
 * <p>UserServiceImpl</p>
 *
 * @author nekoimi 2022/10/16
 */
@Service
public class UserServiceImpl implements UserService
        , JwtSubjectService
{
    @Override
    public JwtSubject loadByIdentifier(String identifier) {
        return User.of("100", "测试账号");
    }
}
