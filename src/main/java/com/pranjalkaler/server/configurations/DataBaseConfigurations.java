package com.pranjalkaler.server.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataBaseConfigurations {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

}
