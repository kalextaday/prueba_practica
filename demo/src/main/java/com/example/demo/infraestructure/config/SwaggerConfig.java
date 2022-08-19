package com.example.demo.infraestructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private Setting setting;

    @Bean
    public Docket swagger() {
        String packageName = getClass().getPackage().getName();
        packageName = packageName.substring(0, packageName.lastIndexOf('.'));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(packageName))
                .build()
                .apiInfo(this.getApiInformation());
    }

    private ApiInfo getApiInformation(){

        Contact contact = new Contact("Kevin Taday", "https://www.kevintaday.cl", "kalexander031098@outlook.com");
        return new ApiInfoBuilder()
                .title("[REST API] ["+setting.getProjectName() +"] Microservicio ["+setting.getAppName()+"].")
                .description("Este microservicio muestra como ejemplo la estructura del proyecto hexagonal, junto con exponer APIs que permiten realizar operaciones sobre cuentas.")
                .version(setting.getVersion())
                .license("® " + LocalDate.now().getYear()+ " - Todos los derechos reservados.")
                .licenseUrl("https://www.kevintaday.io")
                .contact(contact)
                .build();


    }
}
