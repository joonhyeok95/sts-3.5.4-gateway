package com.metanet.study.global.web;

import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.metanet.study.global.domain.ApiResponse;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

  @RequestMapping("/fallback")
  public Mono<String> fallback() {
    return Mono.just("Fallback response");
  }

  @RequestMapping("/fallback/userRoleFallback")
  public Mono<ResponseEntity<ApiResponse<String>>> userRoleFallback(ServerHttpRequest request,
      ServerHttpResponse response) {
    // 상태 코드는 HTTP 응답 상태 코드에서 동적으로 가져올 수 있음
    // HttpStatus status = response.getStatusCode() != null ? response.getStatusCode()
    // : HttpStatus.SERVICE_UNAVAILABLE;
    HttpStatusCode statusCode = response.getStatusCode() != null ? response.getStatusCode()
        : HttpStatus.SERVICE_UNAVAILABLE;
    // 에러 문자열은 상태 코드에서 추출
    String reasonPhrase;

    if (statusCode instanceof HttpStatus) {
      reasonPhrase = ((HttpStatus) statusCode).getReasonPhrase();
    } else {
      // 기본 메시지 혹은 직접 매핑한 메시지를 사용
      reasonPhrase = "Unknown Status";
    }

    // 메시지는 필요에 따라 고정 혹은 추가 로직으로 지정
    String message = "Service is currently unavailable. Please try again later.";
    // Locale은 요청 헤더에서 가져올 수 있음 (예: Accept-Language)
    Locale locale = request.getHeaders().getAcceptLanguageAsLocales().stream().findFirst()
        .orElse(Locale.getDefault());

    // 요청 경로는 ServerHttpRequest에서 추출
    String path = request.getURI().getPath();

    // HttpStatus status, String error, String message, Locale locale, String path
    ApiResponse<String> body =
        new ApiResponse<>(((HttpStatus) statusCode), reasonPhrase, message, locale, path);

    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .contentType(MediaType.APPLICATION_JSON).body(body));

  }
}
