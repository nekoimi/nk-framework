package com.sakuraio.nk.auth.security;

import com.sakuraio.nk.auth.security.config.ShiroConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * nekoimi  2021/12/24 15:06
 *
 * 启用安全访问控制(安全框架使用apache-shiro)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ShiroConfiguration.class)
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public @interface EnableSecurityAccess {
}
