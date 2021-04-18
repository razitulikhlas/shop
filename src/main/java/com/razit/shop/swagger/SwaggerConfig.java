package com.razit.shop.swagger;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.razit.shop.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        ApiInfo info = new ApiInfo(
                "My Demo api",
                "Ini adalah demo API dengan Spring Boot",
                "V1",
                "Terms Of Service",
                new Contact("Razitul ikhlas", "@Razitulikhlas12", "razituli@gmail.com"),
                "Apache Lichense",
                "www.apache.com",
                Collections.emptyList()
        );
        return info;
    }
}
