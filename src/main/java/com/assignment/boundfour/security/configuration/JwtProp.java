package com.assignment.boundfour.security.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProp {

    private String key;
    private Long expiredAt;
}
