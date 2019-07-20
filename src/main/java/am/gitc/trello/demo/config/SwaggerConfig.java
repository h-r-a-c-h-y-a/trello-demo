package am.gitc.trello.demo.config;


import org.springframework.context.annotation.Bean;
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


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("am.gitc.trello.demo.controller"))
                .paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Gitc Trello Demo Api."
                , "Rest Endpoints for Gitc Trello Demo Api"
                , "0.0.1"
                , "Terms of service"
                , new Contact("Gitc Trello Demo", "www.gitc.trello.demo.am", "trello.demo@gitc.com")
                , "License of Gitc Trello Demo Api", "Api license URL", Collections.emptyList());
    }
}


