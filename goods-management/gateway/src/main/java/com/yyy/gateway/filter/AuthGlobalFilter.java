package com.yyy.gateway.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;

@Component
@Order(0)
public class AuthGlobalFilter implements GlobalFilter {

    // 三处密钥必须完全一致
    private final String SECRET = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmn";
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();

        // 登录接口直接放行，不校验token
        if (path.contains("/auth/login")) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        token = token.substring(7);
        // 清除空字符 \0
        token = token.replace("\0", "");

        try {
            // jjwt‑0.12.5新版解析语法
            Claims claims = Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Long userId = claims.get("userId", Long.class);
            // 向下游服务透传userId头
            ServerHttpRequest newRequest = request.mutate()
                    .header("X-User-Id", userId.toString())
                    .build();
            return chain.filter(exchange.mutate().request(newRequest).build());

        } catch (JwtException e) {
            // token非法、过期、篡改
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
    }
}
