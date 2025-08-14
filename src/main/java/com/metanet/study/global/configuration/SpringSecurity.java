package com.metanet.study.global.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SpringSecurity {
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    return http.authorizeExchange(exchanges -> exchanges.anyExchange().permitAll())
        .csrf(csrf -> csrf.disable()).build();
  }

}
