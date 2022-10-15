package com.sakuraio.nk.auth.api.contract;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>JwtSubject</p>
 * <p>
 * Jwt认证对象，需要参与验证的实体类需要扩展此接口
 *
 * @author nekoimi 2022/10/14
 */
public interface JwtSubject extends Serializable {

    /**
     * <p>唯一标识</p>
     *
     * @return
     */
    String getIdentifier();

    /**
     * <p>获取显示名称</p>
     *
     * @return
     */
    String getUsername();

    default boolean isAccountNonExpired() {
        return true;
    }

    default boolean isAccountNonLocked() {
        return true;
    }

    default Map<String, String> getJwtCustomClaims() {
        return Maps.newHashMap();
    }
}
