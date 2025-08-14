package com.metanet.study.global.filter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

  @RequestMapping("/fallback")
  public Mono<String> fallback() {
    return Mono.just("Fallback response");
  }

  @RequestMapping("/fallback/userRoleFallback")
  public Mono<String> userRoleFallback() {
    return Mono.just("Service is temporarily unavailable. Please try again later.");
  }
}
