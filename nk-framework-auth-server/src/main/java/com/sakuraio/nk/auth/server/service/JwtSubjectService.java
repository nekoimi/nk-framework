package com.sakuraio.nk.auth.server.service;

import com.sakuraio.nk.auth.api.contract.JwtSubject;

/**
 * <p>JwtSubjectService</p>
 *
 * @author nekoimi 2022/10/16
 */
public interface JwtSubjectService {

    /**
     * <p>使用唯一标识获取认证对象</p>
     *
     * @param identifier
     * @return
     */
    JwtSubject loadByIdentifier(String identifier);
}
