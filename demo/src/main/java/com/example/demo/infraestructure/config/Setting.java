package com.example.demo.infraestructure.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Setting {
    @Value("${spring.app.version}")
    private String version;

    @Value("${spring.project.name}")
    private String projectName;

    @Value("${spring.application.name}")
    private String appName;
}
