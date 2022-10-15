package com.sakuraio.nk.auth.api.handler;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sakuraio.nk.auth.api.contract.AccessHandler;
import com.sakuraio.nk.auth.api.contract.JwtCacheManager;
import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.exception.TokenCannotBeRefreshException;
import com.sakuraio.nk.auth.api.exception.TokenExpireException;
import com.sakuraio.nk.auth.api.exception.TokenInvalidException;
import com.sakuraio.nk.auth.api.jwt.JwtUtils;
import com.sakuraio.nk.auth.api.sso.SsoContext;
import com.sakuraio.nk.constants.RequestConstants;
import com.sakuraio.nk.error.exception.RequestValidationException;
import com.sakuraio.nk.util.http.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>DefaultAccessHandler</p>
 * <p>
 * 请求授权验证，当前验证框架主要验证token是否合法，初始化SsoContext的认证信息
 *
 * @author nekoimi 2022/10/15
 */
@Slf4j
public class DefaultAccessHandler implements AccessHandler {
    private final JwtCacheManager cacheManager;

    public DefaultAccessHandler(JwtCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public synchronized void handleAccess(HttpServletRequest request, HttpServletResponse response) {
        String token = RequestUtils.getToken(request);
        if (StringUtils.isBlank(token)) {
            throw new RequestValidationException("缺少token值");
        }

        boolean needRefreshToken = false;
        String finalToken = token;
        try {
            JwtUtils.checkToken(token);
        } catch (TokenExpiredException expiredException) {
            // token过期，尝试刷新token
            try {
                // tips: 这里需要考虑一种并发请求情况
                //       并发请求过来，这些请求都带着过期的token一起请求过来
                //       对第一个请求的token进行了刷新处理，后续的带着过期token过来的请求其实是不用再次刷新的
                //       所以这里需要添加一个短期的token置换缓存，用旧的过期的token置换已经刷新的token
                //       先到并发缓存(过期的token => 刷新后的token)里面查找对应的已经刷新好的token
                String refreshedToken = cacheManager.getRefreshedToken(token);
                if (StringUtils.isBlank(refreshedToken)) {
                    // 没有找到并发缓存，检查改token是否被放到黑名单，放到黑名单的token不允许再次刷新
                    if (cacheManager.blackHas(token)) {
                        throw new TokenExpireException();
                    }
                    // 过期的token没有被刷新过，检查是否可以刷新
                    JwtUtils.checkRefresh(token);
                    needRefreshToken = true;
                } else {
                    // 替换过期的token
                    finalToken = refreshedToken;
                }
            } catch (TokenCannotBeRefreshException cannotBeRefreshException) {
                log.debug(cannotBeRefreshException.getMessage(), cannotBeRefreshException);
                if (log.isDebugEnabled()) {
                    cannotBeRefreshException.printStackTrace();
                }
                throw new TokenExpireException();
            }
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new TokenInvalidException();
        }

        String sub = JwtUtils.decodeSub(finalToken);
        // TODO 根据 sub 查询认证对象
        JwtSubject subject = null;
        if (subject == null) {
            log.error("token验证异常，解析token获取sub，使用sub查找认证对象为空，sub: {}", sub);
            throw new TokenInvalidException();
        }

        if (needRefreshToken) {
            finalToken = JwtUtils.encode(subject);
            // 将过期的token放置到黑名单
            cacheManager.blackAdd(token);
            // 添加刷新置换并发缓存
            cacheManager.setRefresh(token, finalToken);
            // 添加刷新token响应头
            response.addHeader(RequestConstants.HEADER_AUTHORIZATION, "Bearer " + finalToken);
        }

        // 保存认证上下文
        SsoContext.setToken(finalToken);
        SsoContext.setIdentifier(subject.getIdentifier());
        SsoContext.setJwtSubject(subject);
    }
}
