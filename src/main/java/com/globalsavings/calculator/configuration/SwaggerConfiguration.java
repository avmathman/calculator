package com.globalsavings.calculator.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Configuration of the application Swagger specification 2.0.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /** API prefix for rest endpoints, based on profile settings. */
    @Value("${calculator.api.prefix:}")
    private String apiPrefix;

    /**
     * Create a summary of the contents of a document.
     *
     * @return {@link Docket} with swagger document summary.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .apiInfo(apiInfo())
                .select()
                .paths(regex(apiPrefix + "/.*"))
                .apis(RequestHandlerSelectors.basePackage("com.globalsavings.calculator"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Calculator REST API")
                .description("Calculator application allows to perform crud on Calculate and Region API.")
                .version("Version 1.0")
                .build();
    }
}
