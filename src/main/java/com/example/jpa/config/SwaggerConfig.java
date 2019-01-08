package com.example.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        List<ResponseMessage> responseMessageStatus = Arrays.asList(
                new ResponseMessageBuilder().code(400).message("Invalid Request").responseModel(new ModelRef("Error")).build(),
                new ResponseMessageBuilder().code(401).message("No Permissoin").build(),
                new ResponseMessageBuilder().code(500).message("Error").build()
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageStatus)
                .globalResponseMessage(RequestMethod.POST, responseMessageStatus)
                .globalResponseMessage(RequestMethod.PUT, responseMessageStatus)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageStatus);

    }




}
