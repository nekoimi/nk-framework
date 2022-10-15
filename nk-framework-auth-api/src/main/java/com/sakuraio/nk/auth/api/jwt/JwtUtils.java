package com.sakuraio.nk.auth.api.jwt;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sakuraio.nk.auth.api.contract.JwtSubject;
import com.sakuraio.nk.auth.api.exception.TokenCannotBeRefreshException;
import org.apache.commons.collections4.MapUtils;

import java.util.Date;
import java.util.Map;

/**
 * <p>JwtUtils</p>
 *
 * @author nekoimi 2022/10/15
 */
public class JwtUtils {
    private static final String REFRESH_KEY = "rat";
    // jwt签发者
    private static final String ISSUER = "com.sakuraio.nk.auth.api.jwt";
    // jwt加密算法
    private static Algorithm algorithm;
    // token有效期
    private static int tokenTTL = 0;
    // token刷新有效期
    private static int tokenRefreshTTL = 0;

    private JwtUtils() {
    }

    /**
     * <p>生成token</p>
     *
     * @param subject
     * @return
     */
    public static String encode(JwtSubject subject) {
        Date now = new Date();
        JWTCreator.Builder builder = JWT.create();
        builder.withIssuer(ISSUER);
        builder.withIssuedAt(now);
        builder.withSubject(subject.getIdentifier());
        builder.withExpiresAt(DateUtil.offsetSecond(now, tokenTTL));
        builder.withClaim(REFRESH_KEY, DateUtil.offsetSecond(now, tokenRefreshTTL));
        // 添加自定义参数
        Map<String, String> jwtCustomClaims = subject.getJwtCustomClaims();
        if (MapUtils.isNotEmpty(jwtCustomClaims)) {
            for (Map.Entry<String, String> entry : jwtCustomClaims.entrySet()) {
                builder.withClaim(entry.getKey(), entry.getValue());
            }
        }
        return builder.sign(algorithm);
    }

    /**
     * <p>解析token</p>
     *
     * @param token
     * @return
     */
    public static DecodedJWT decode(String token) {
        return JWT.decode(token);
    }

    /**
     * <p>解析sub</p>
     *
     * @param token
     * @return
     */
    public static String decodeSub(String token) {
        DecodedJWT decodedJWT = decode(token);
        return decodedJWT.getSubject();
    }

    /**
     * <p>检查验证token</p>
     *
     * @param token
     */
    public static void checkToken(String token) {
        JWT.require(algorithm).withIssuer(ISSUER).build().verify(token);
    }

    /**
     * <p>检查token是否可以刷新</p>
     *
     * @param token
     */
    public static void checkRefresh(String token) {
        DecodedJWT decodedJWT = decode(token);
        Claim decodedJWTClaim = decodedJWT.getClaim(REFRESH_KEY);
        Date today = new Date();
        today.setTime(today.getTime() / 1000L * 1000L);
        Date refreshAt = decodedJWTClaim.asDate();
        if (refreshAt != null && today.after(refreshAt)) {
            throw new TokenCannotBeRefreshException();
        }
    }

    public static void setAlgorithm(String secret) {
        JwtUtils.algorithm = Algorithm.HMAC256(secret);
    }

    public static void setTokenTTL(int tokenTTL) {
        JwtUtils.tokenTTL = tokenTTL;
    }

    public static void setTokenRefreshTTL(int tokenRefreshTTL) {
        JwtUtils.tokenRefreshTTL = tokenRefreshTTL;
    }
}
