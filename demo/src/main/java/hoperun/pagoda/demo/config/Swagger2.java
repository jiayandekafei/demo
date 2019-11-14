package hoperun.pagoda.demo.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger config.
 *
 * @author zhangxiqin
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * Init Docket.
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("hoperun.pagoda.demo.controller")).paths(PathSelectors.any()).build()
                .securityContexts(securityContexts()).securitySchemes(securitySchemes());
    }

    /**
     * Api info.
     *
     * @return ApiInfo Api Info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Pagoda API").description("Pagoda API").version("1.0").build();
    }

    /**
     * Get scheme key.
     *
     * @return List<ApiKey> api key
     */
    private List<ApiKey> securitySchemes() {

        return Lists.newArrayList(new ApiKey("Authorization", "Authorization", "header"));

    }

    /**
     * Get content.
     *
     * @return List<SecurityContext> Security Context
     */
    private List<SecurityContext> securityContexts() {

        SecurityContext context = SecurityContext.builder().securityReferences(defaultAuth()).build();

        return Lists.newArrayList(context);

    }
    /**
     * Security Reference.
     * 
     * @return List<SecurityReference>
     */
    private List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("Authorization", authorizationScopes));

    }

}