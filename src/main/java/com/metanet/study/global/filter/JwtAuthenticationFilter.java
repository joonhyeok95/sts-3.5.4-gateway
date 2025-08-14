package com.metanet.study.global.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter
    extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {
  // JWT 검증, 권한 헤더 파싱 후, 실패 시 401 반환 등 처리

  public JwtAuthenticationFilter() {
    super(Config.class);
  }

  // Config 클래스 정의 (비어 있어도 무방)
  public static class Config {
    // 여기에 필요한 설정 필드를 추가 가능
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      // JWT 검증 로직 구현
      // 실패 시 예:
      // exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      // return exchange.getResponse().setComplete();
      return chain.filter(exchange);
    };
  }
}
