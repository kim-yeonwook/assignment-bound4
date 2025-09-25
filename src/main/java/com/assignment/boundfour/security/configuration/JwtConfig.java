package com.assignment.boundfour.security.configuration;

import com.assignment.boundfour.security.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(JwtProp.class)
public class JwtConfig {

    @Bean
    public JwtProvider jwtTokenProvider(JwtProp prop) {
        log.info(
                "secret_key : {}\nexpired_access_token : {}",
                prop.getKey(),
                prop.getExpiredAt()
        );

        return new JwtProvider(
                prop.getKey(),
                prop.getExpiredAt()
        );
    }
}
