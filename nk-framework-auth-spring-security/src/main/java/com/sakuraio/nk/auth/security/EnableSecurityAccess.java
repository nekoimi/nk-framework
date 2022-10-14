package com.sakuraio.nk.auth.security;

import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.*;

/**
 * nekoimi  2021/12/24 15:06
 *
 * 启用安全访问控制(安全框架使用spring-security)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableWebSecurity
//@Import(SecurityAccessConfiguration.class)
@Order(Ordered.LOWEST_PRECEDENCE - 100)
public @interface EnableSecurityAccess {
}
