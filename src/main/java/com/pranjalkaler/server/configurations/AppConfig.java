package com.pranjalkaler.server.configurations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DataBaseConfigurations.class)
public class AppConfig {

}