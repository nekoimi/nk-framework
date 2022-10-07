package com.sakuraio.nk.swagger2.config;

import com.google.common.collect.Lists;
import com.sakuraio.nk.core.constants.Headers;
import com.sakuraio.nk.core.utils.SpringPropertyUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Swagger2Configuration</p>
 *
 * @author nekoimi 2022/10/07
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Configuration {

    @Value("${nk.swagger.permit-all}")
    private List<String> permitAll = Lists.newArrayList();

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(SpringPropertyUtils.applicationName())
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SpringPropertyUtils.applicationName() + " - API接口文档")
                .description(SpringPropertyUtils.applicationName() + " API接口文档")
                .version("1.0")
                .license("APACHE LICENSE, VERSION 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("nk-framework", "#", "#"))
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey(Headers.AUTHORIZATION, Headers.AUTHORIZATION, "header"));
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(this::pathSelect)
                .build());
        return securityContexts;
    }

    private boolean pathSelect(String path) {
        AntPathMatcher matcher = new AntPathMatcher();
        boolean b = !matcher.match("/", path);
        for (String s : permitAll) {
            b = !matcher.match(s, path);
        }
        return b;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference(Headers.AUTHORIZATION, authorizationScopes));
        return securityReferences;
    }
}
