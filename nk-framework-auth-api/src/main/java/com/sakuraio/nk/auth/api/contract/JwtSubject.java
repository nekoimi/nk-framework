package com.sakuraio.nk.auth.api.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    String getIdentifier();

    /**
     * <p>获取显示名称</p>
     *
     * @return
     */
    String getUsername();

    @JsonIgnore
    default boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    default boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    default Map<String, String> getJwtCustomClaims() {
        return Maps.newHashMap();
    }
}
